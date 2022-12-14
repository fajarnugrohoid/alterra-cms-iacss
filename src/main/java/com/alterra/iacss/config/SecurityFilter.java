package com.alterra.iacss.config;

import com.alterra.iacss.logging.LoggingFilter;
import com.alterra.iacss.util.CustomHttpServletRequestWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Log4j2
@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private static final String JWT_HEADER = "Authorization";
    private static final String JWT_PREFIX = "Bearer";

    private final JwtTokenProvider jwtTokenProvider;
    private final LoggingFilter filter;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        CustomHttpServletRequestWrapper wrapper = new CustomHttpServletRequestWrapper(request);

        String requestBody = wrapper.getBody();
        try {
            String token = getJwtFromRequest(request);
            log.info( "token:" + token );
            if (token != null && !token.isBlank() && jwtTokenProvider.validateToken(token, request, requestBody)) {
                String username = jwtTokenProvider.getUsername(token);

                UserDetails user = userDetailsService.loadUserByUsername(username);

                log.info("user : {}", user);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                        user.getPassword(), user.getAuthorities());
                log.info("authenticationToken : {}", authenticationToken);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        filterChain.doFilter(wrapper, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWT_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
    private String getStringValue(byte[] contentAsByteArray) {
        try {
            return new String(contentAsByteArray, StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}

    package com.mover.security;

    import com.mover.entities.User;
    import com.mover.repositories.UserRepository;
    import io.jsonwebtoken.ExpiredJwtException;
    import io.jsonwebtoken.MalformedJwtException;
    import io.jsonwebtoken.security.SignatureException;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;

    @Component
    @Slf4j
    public class JwtAuthFilter extends OncePerRequestFilter {

        @Autowired
        private UserRepository userRepo;

        @Autowired
        private AuthUtil authUtil;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String requestTokenHeader = request.getHeader("Authorization");

            if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            try {
                String token = requestTokenHeader.substring(7).trim();
                if(token.isEmpty()) {
                    log.warn("Empty JWT token received");
                    filterChain.doFilter(request, response);
                    return;
                }
                String username = authUtil.getUserNameFromToken(token);
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = userRepo.findByEmail(username).orElseThrow(() ->
                            new RuntimeException("User not found with email: " + username));

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.debug("User '{}' authenticated successfully", username);
                }

            } catch (MalformedJwtException e) {
                log.error("Invalid JWT token format: {}", e.getMessage());
            } catch (ExpiredJwtException e) {
                log.error("JWT token has expired: {}", e.getMessage());
            } catch (SignatureException e) {
                log.error("JWT signature validation failed: {}", e.getMessage());
            } catch (IllegalArgumentException e) {
                log.error("JWT token is invalid: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Error processing JWT token: {}", e.getMessage());
            }
            filterChain.doFilter(request, response);
        }
    }
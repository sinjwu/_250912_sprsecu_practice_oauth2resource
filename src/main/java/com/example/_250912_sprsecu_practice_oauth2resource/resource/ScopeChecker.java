package com.example._250912_sprsecu_practice_oauth2resource.resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Component
public class ScopeChecker {
    public boolean canAccessUser(Long userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof JwtAuthenticationToken jwtAuth)) {
            return false;
        }
        Jwt jwt = jwtAuth.getToken();
        if (hasScope(auth, "admin")) {
            return true;
        }
        if (hasScope(auth, "read:users")) {
            String tokenUserId = jwt.getClaimAsString("userId");
            return userId.toString().equals(tokenUserId);
        }
        return false;
    }
    public boolean canModifyUser(Long userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return hasScope(auth, "admin") || hasScope(auth, "write:users");
    }
    private boolean hasScope(Authentication auth, String scope) {
        return auth.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("SCOPE_" + scope)
        );
    }
}

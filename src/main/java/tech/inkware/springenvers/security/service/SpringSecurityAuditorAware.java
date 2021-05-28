package tech.inkware.springenvers.security.service;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jeanclaude.bucumi
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();
        if (authetication != null && authetication.isAuthenticated()) {
            return Optional.of(authetication.getName());
        }
        return Optional.empty();
    }

}

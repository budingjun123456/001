package xyz.javaee.psychology_questionnaire.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import xyz.javaee.psychology_questionnaire.entity.User;
import xyz.javaee.psychology_questionnaire.service.UserService;

@Component
@AllArgsConstructor
public class CurrentUserUtils {

    private UserService userService;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return userService.login((String) authentication.getPrincipal());
        }
        return null;
    }
}

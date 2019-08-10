package academy.devdojo.youtube.auth.web.rest.impl;

import academy.devdojo.youtube.auth.web.rest.UserController;
import academy.devdojo.youtube.core.domain.ApplicationUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @GetMapping("/user/info")
    public ResponseEntity<ApplicationUser> getUserInfo(UsernamePasswordAuthenticationToken token) {
        ApplicationUser applicationUser = (ApplicationUser) token.getPrincipal();

        return ResponseEntity.ok(applicationUser);
    }
}

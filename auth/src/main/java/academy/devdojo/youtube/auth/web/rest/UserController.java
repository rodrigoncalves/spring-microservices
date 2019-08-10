package academy.devdojo.youtube.auth.web.rest;

import academy.devdojo.youtube.core.domain.ApplicationUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Api("Endpoints to manage information of user")
public interface UserController {

    @ApiOperation(value = "Will retrieve the information from the user available in the token", response = ApplicationUser.class)
    ResponseEntity<ApplicationUser> getUserInfo(UsernamePasswordAuthenticationToken token);
}

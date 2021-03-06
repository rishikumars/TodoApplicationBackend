package basicauth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BasicAuthenticationController {
    @GetMapping("/basicauthlogin")
    public ResponseEntity<Void> basicAuthentication(){
        return ResponseEntity.ok().build();
    }
}

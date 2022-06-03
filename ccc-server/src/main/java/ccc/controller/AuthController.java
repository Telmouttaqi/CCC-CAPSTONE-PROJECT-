package ccc.controller;

import ccc.domain.Result;
import ccc.models.AppUser;
import ccc.security.AppUserService;
import ccc.security.JwtConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager manager;
    private final JwtConverter jwtConverter;
    private final AppUserService service;

    public AuthController(AuthenticationManager manager, JwtConverter jwtConverter, AppUserService service) {
        this.manager = manager;
        this.jwtConverter = jwtConverter;
        this.service = service;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = manager.authenticate(token);
        if (authentication.isAuthenticated()) {
            String jwtToken = jwtConverter.getTokenFromUser((User) authentication.getPrincipal());
            HashMap<String, String> whateverMap = new HashMap<>();
            whateverMap.put("jwt_token", jwtToken);
            return new ResponseEntity<>(whateverMap, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @PostMapping("/create_account")
    public ResponseEntity<Object> createAccount(@RequestBody Map<String, String> credentials) {

        String username = credentials.get("username");
        String password = credentials.get("password");
        String firstName = credentials.get("firstName");
        String middleName = credentials.get("middleName");
        String lastName = credentials.get("lastName");
        String address = credentials.get("userAddress");
        String city = credentials.get("userCity");
        String state = credentials.get("userState");
        String zip = credentials.get("userZip");
        String phone = credentials.get("userPhone");
        String email = credentials.get("userEmail");

        Result<AppUser> result = service.create(username, password, firstName, middleName, lastName, address, city, state, zip, phone, email);
        if(result.isSuccess()) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("appUserId", result.getPayload().getAppUserId());

            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }
    @GetMapping("/appuser/{userName}")
    public AppUser findByUserName(@PathVariable String userName){
        return service.findByUserName(userName);
    }

}


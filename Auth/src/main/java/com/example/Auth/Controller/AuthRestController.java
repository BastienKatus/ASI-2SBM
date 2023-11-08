package com.example.Auth.Controller;

import com.example.CommonLib.AuthDTO;
import com.example.CommonLib.UserDTO;
import org.springframework.web.bind.annotation.*;

//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(method=RequestMethod.POST,value="/login")
    public UserDTO login(@RequestBody AuthDTO auth) {
        return authService.loginUser(auth);
    }


    @RequestMapping(method=RequestMethod.POST,value="/register")
    public UserDTO register(@RequestBody UserDTO user) {
        return authService.registerUser(user);
    }
}

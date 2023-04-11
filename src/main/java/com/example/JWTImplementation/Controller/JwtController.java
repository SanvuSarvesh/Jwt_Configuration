package com.example.JWTImplementation.Controller;

import com.example.JWTImplementation.Model.JwtRequest;
import com.example.JWTImplementation.Model.JwtResponse;
import com.example.JWTImplementation.Service.CustomUserDetailsService;
import com.example.JWTImplementation.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController

public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    //@PostMapping("/register")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest)
            throws Exception {
        System.out.println(jwtRequest);

        try {
            this.authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken
                            (jwtRequest.getUsername(),jwtRequest.getPassword()));
        }
        catch (UsernameNotFoundException exception){
            exception.printStackTrace();
            throw new Exception("Invalid credentials.");
        }
        catch (Exception exception){
            throw new Exception("INVALID_USER");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername
                (jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        //String token = this.jwtRequest.generateToken(userDetails);
        System.out.println("Token is :- "+jwtRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

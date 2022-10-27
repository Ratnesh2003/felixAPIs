//package com.felix.felixapis.security.jwt;
//
//import com.felix.felixapis.models.User;
//import com.felix.felixapis.payload.request.LoginRequest;
//import com.felix.felixapis.security.services.UserDetailsImpl;
//import com.felix.felixapis.security.services.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.*;
//
////@RestController
////@RequestMapping("/")
//public class JwtController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//
////    @PostMapping("/api/auth/login")
//    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {
//        try {
//            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        } catch (UsernameNotFoundException ex) {
//            ex.printStackTrace();
//            throw new Exception("Invalid Email or Password");
//        }
//
//        UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getEmail());
//
//        String token = this.jwtUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new JwtResponse());
//    }
//}

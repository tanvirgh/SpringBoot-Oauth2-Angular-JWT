package com.cmed.health.app.controller;

import javax.servlet.http.HttpServletRequest;

import com.cmed.health.app.security.jwt.JwtAuthenticationRequest;
import com.cmed.health.app.security.jwt.JwtAuthenticationResponse;
import com.cmed.health.app.security.jwt.JwtTokenUtil;
import com.cmed.health.core.dto.ActorUser;
import com.cmed.health.core.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername().trim(),
                        authenticationRequest.getPassword().trim()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final ActorUser userDetails = (ActorUser) securityService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(authentication,userDetails, device);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token,userDetails.getUsername(),userDetails.getUserId(),
                userDetails.getSurveyorId(),userDetails.isAdmin(), userDetails.isManager(), userDetails.isLead(),
                userDetails.getAuthorities()));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        ActorUser user = (ActorUser) securityService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken,user.getUsername(),user.getUserId(),
                                        user.isAdmin(), user.isManager(), user.isLead()));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

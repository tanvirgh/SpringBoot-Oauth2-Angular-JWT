package com.cmed.health.app.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -7591547359974919829L;

    private final String token;
    private final String username;
    private final Long userId;
    private  Long surveyorId;
    private  Boolean isAdmin;
    private  Boolean isManager;
    private  Boolean isLead;
    private Set<? extends GrantedAuthority> authorities;

    public JwtAuthenticationResponse(String token,String username,Long userId,
                                     Boolean isAdmin) {
        this.token = token;
        this.username = username;
        this.userId = userId;
        this.isAdmin = isAdmin;
    }
    public JwtAuthenticationResponse(String token,String username,Long userId,
                                     Boolean isAdmin, Boolean isManager,
                                     Boolean isLead) {
        this(token, username, userId, isAdmin);
        this.isManager = isManager;
        this.isLead = isLead;
    }

    public JwtAuthenticationResponse(String token,String username,Long userId, Long surveyorId,
                                     Boolean isAdmin, Boolean isManager,
                                     Boolean isLead,
                                     Set<? extends GrantedAuthority> authorities) {
        this(token, username, userId, isAdmin, isManager, isLead);
        this.surveyorId = surveyorId;
        this.authorities = authorities;
    }

}

package com.cmed.health.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true)
public class ActorUser implements UserDetails{
	private Long userId;
	private String email;
    private Long surveyorId;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean isAdmin;
    private boolean isManager;
    private boolean isLead;
    private Date lastPasswordResetDate;
    private Set<? extends GrantedAuthority> authorities;
}

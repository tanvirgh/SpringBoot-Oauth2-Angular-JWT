package com.cmed.health.core.entity;

import com.cmed.health.core.dto.ActorUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User extends BaseEntity {

    public User(){
        this.setUsername(this.getEmail());
        if(this.getRoles()!=null && this.getRoles().size()>0) {
            Optional<Role> roleOptional = this.getRoles().stream().findFirst();
            if(roleOptional.isPresent()) {
                Role role = roleOptional.get();
                this.roleName = role.getName();
            }

        }
    }

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    @ColumnDefault("1")
    private Boolean enabled;

    @Column(name = "expired")
    @ColumnDefault("0")
    private Boolean expired;

    @Column(name = "locked")
    @ColumnDefault("0")
    private Boolean locked;

    @Column(name = "last_password_reset_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //@NotNull
    @Builder.Default
    private Date lastPasswordResetDate = new Date();



    //TODO: should be depricated
    @Column(name = "hasLoggedIn")
    @ColumnDefault("0")
    private Boolean hasLoggedIn;


    @Transient
    private String roleName;

    public String getRoleName(){
        if(this.getRoles()!=null && this.getRoles().size()>0) {
            Optional<Role> roleOptional = this.getRoles().stream().findFirst();
            if(roleOptional.isPresent()) {
                Role role = roleOptional.get();
                this.roleName = role.getName();
            }

        }
        return this.roleName;
    }

    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    //TODO: potential violation of SRP. some refactoring could done
    public ActorUser toActor(){
        return ActorUser.builder()
                .userId(getId())
                .username(getEmail())
                .email(getEmail())
                .password(getPassword())
                .enabled(getPrimitiveNonNUllBoolean(getEnabled()))
                .accountNonExpired(!getPrimitiveNonNUllBoolean(getExpired()))
                .accountNonLocked(!getPrimitiveNonNUllBoolean(getLocked()))
                .credentialsNonExpired(!getPrimitiveNonNUllBoolean(getExpired()))
                .isAdmin(isUserAdmin())
                .isManager(isUserManager())
                .isLead(isUserLead())
                .authorities(getAuthorities(getRoles()))
                .lastPasswordResetDate(getLastPasswordResetDate())
                .build();
    }

    private boolean getPrimitiveNonNUllBoolean( Boolean val) {
        return val ==null ? false :val.booleanValue();
    }

    private Set<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    //TODO: may could be placed at UserService/SecurityService
    private boolean isUserAdmin(){
        return this.getRoles() != null && this.getRoles().stream()
                .filter(role ->role.getName().equals("ADMIN"))
                .findAny()
                .isPresent();
    }

    private boolean isUserManager(){
        return this.getRoles() != null && this.getRoles().stream()
                .filter(role ->role.getName().equals("MANAGER"))
                .findAny()
                .isPresent();
    }
    private boolean isUserLead(){
        return this.getRoles() != null && this.getRoles().stream()
                .filter(role ->role.getName().equals("LEAD"))
                .findAny()
                .isPresent();
    }

}

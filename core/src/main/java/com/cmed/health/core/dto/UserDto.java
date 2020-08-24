package com.cmed.health.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Getter
@Setter
@ToString
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private String roleName;

    private boolean enabled = true;

    private boolean expired;

    private boolean locked;



    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDto)) {
            return false;
        }

        UserDto user = (UserDto) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

}

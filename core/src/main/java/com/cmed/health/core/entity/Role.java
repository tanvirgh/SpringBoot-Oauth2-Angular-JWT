package com.cmed.health.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true,exclude = "users")
@EqualsAndHashCode(callSuper=true, exclude = {"users"} )
@Builder(toBuilder = true)
@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    private static final long serialVersionUID = -3095969477787503280L;

    @Column(name = "name")
    private String name;

    @Column
    private String description;


}

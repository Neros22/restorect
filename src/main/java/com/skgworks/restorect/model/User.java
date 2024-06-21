package com.skgworks.restorect.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Document
@AllArgsConstructor
public final class User extends BaseModel implements UserDetails {

    private String username;

    private String name, surname, email, password;

    private LocalDate birthdate;
    private boolean isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled;

    @DBRef(lazy = true)
    private Set<UserRole> userRoles;

    @DBRef(lazy = true)
    private Set<UserPermission> userPermissions;

    public int getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        //For user roles
        if ((!CollectionUtils.isEmpty(getUserRoles()))) {
            getUserRoles().forEach(applicationUserRole -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + applicationUserRole.getName())));
        }

        //For permissions
        if (!CollectionUtils.isEmpty(getUserPermissions())) {
            getUserPermissions().forEach(applicationUserPermission -> grantedAuthorities.add(new SimpleGrantedAuthority(applicationUserPermission.getName())));
        }

        return grantedAuthorities;
    }

}

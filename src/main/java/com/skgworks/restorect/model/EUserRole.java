package com.skgworks.restorect.model;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.skgworks.restorect.model.EUserPermission.*;

@Getter
@RequiredArgsConstructor
public enum EUserRole {
    USER(Sets.newHashSet(USER_READ, USER_WRITE)), ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE));

    private final Set<EUserPermission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority(("ROLE_" + this.name())));
        return permissions;
    }

}

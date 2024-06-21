package com.skgworks.restorect.endpoints;

import com.skgworks.restorect.dto.CreateUserDto;
import com.skgworks.restorect.dto.UserDto;

import com.skgworks.restorect.model.*;
import com.skgworks.restorect.service.UserService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import jakarta.annotation.security.PermitAll;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Endpoint
@AnonymousAllowed
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserService userService;

    @PermitAll
    public UserDto createUser(CreateUserDto userDto) {
        Set<EUserRole> roles = !userDto.roles().isEmpty() ? userDto.roles().stream().map(EUserRole::valueOf).collect(Collectors.toSet()) : null;
        Set<EUserPermission> eUserPermissions = roles != null ? roles.stream().flatMap(role -> role.getPermissions().stream()).collect(Collectors.toSet()) : null;
        Set<UserRole> userRoles = roles != null ? roles.stream().map(role -> new UserRole(role.name())).collect(Collectors.toSet()) : null;
        Set<UserPermission> permissions = eUserPermissions != null ? eUserPermissions.stream().map(
                permission -> new UserPermission(permission.name(), permission.getPermission())
        ).collect(Collectors.toSet()) : null;

        User user = new User(userDto.username(), userDto.name(), userDto.surname(), userDto.email(), userDto.password(),
                userDto.birthdate(), true, true, true, true,
                userRoles, permissions);
        user = userService.create(user);
        return new UserDto(user.getUsername(), user.getName(), user.getSurname(), user.getEmail(),
                "", user.getBirthdate(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(), user.getCreated(), user.getUpdated());
    }

    @PermitAll
    public UserDto getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        final List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new UserDto(auth.getName(), authorities);
    }

}

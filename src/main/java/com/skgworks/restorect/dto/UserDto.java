package com.skgworks.restorect.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.skgworks.restorect.model.User}
 */
public record UserDto(String username, String name, String surname, String email,
                      String password, LocalDate birthdate, List<String> roles, Date created,
                      Date updated) implements Serializable {
    public UserDto(String username, List<String> roles) {
        this(username, "", "", "", "", LocalDate.now(), roles, new Date(), new Date());
    }
}
package com.skgworks.restorect.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record CreateUserDto(String username, String name, String surname, String email,
                            String password, String confirmPassword, LocalDate birthdate, List<String> roles,
                            Date created, Date updated) implements Serializable {
}
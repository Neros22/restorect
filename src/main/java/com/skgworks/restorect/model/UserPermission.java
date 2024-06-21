package com.skgworks.restorect.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public final class UserPermission {
    @Id
    private String name;

    private String value;

}

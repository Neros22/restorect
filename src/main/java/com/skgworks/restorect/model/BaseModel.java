package com.skgworks.restorect.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public abstract sealed class BaseModel permits User, Item {

    @Id
    private String id;

    private Date created, updated;

}

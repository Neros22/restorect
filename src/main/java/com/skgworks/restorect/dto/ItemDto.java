package com.skgworks.restorect.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.skgworks.restorect.model.Item}
 */
public record ItemDto(String name, double price, String description, Date created,
                      Date updated) implements Serializable {
}
package com.skgworks.restorect.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection = "items")
public final class Item extends BaseModel {

    @NonNull
    private String name;

    private double price;

    private String description;
}

package com.skgworks.restorect.endpoints;

import com.skgworks.restorect.dto.ItemDto;
import com.skgworks.restorect.model.Item;
import com.skgworks.restorect.service.ItemService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@Endpoint
@AnonymousAllowed
@RequiredArgsConstructor
public class ItemEndpoint {

    private final ItemService itemService;

    @PermitAll
    public List<ItemDto> getItems() {
        List<Item> items = itemService.getAll();
        if (items != null && !items.isEmpty()) {
            return items.stream()
                    .map(item -> new ItemDto(item.getName(), item.getPrice(), item.getDescription(), item.getCreated(), item.getUpdated()))
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    @PermitAll
    public ItemDto saveItem(ItemDto itemDto) {

        Item item = new Item(itemDto.name(), itemDto.price(), itemDto.description());
        itemService.create(item);
        return new ItemDto(item.getName(), item.getPrice(), item.getDescription(), item.getCreated(), item.getUpdated());
    }

    @PermitAll
    public boolean deleteItem(String id) {
        return itemService.delete(id);
    }

    @PermitAll
    public ItemDto getItem(String id) throws Exception {
        Item item = itemService.getById(id);
        return new ItemDto(item.getName(), item.getPrice(), item.getDescription(), item.getCreated(), item.getUpdated());
    }

}

package com.skgworks.restorect.service;

import com.skgworks.restorect.model.Item;
import com.skgworks.restorect.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public final class ItemService extends AbstractService<Item, ItemRepository> {

    public ItemService(ItemRepository itemRepository) {
        super(itemRepository);
    }

}

package com.skgworks.restorect;

import com.skgworks.restorect.model.Item;
import com.skgworks.restorect.repository.ItemRepository;
import com.skgworks.restorect.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataMongoTest
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    /*
        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            itemService = new ItemService(itemRepository);
        }
    */
    @Test
    void testCreateItem() {
        // Arrange
        Item item = new Item("1", 10.0, "");
        when(itemRepository.save(item)).thenReturn(item);

        // Act
        Item createdItem = itemService.create(item);

        // Assert
        assertEquals(item, createdItem);
    }

    @Test
    void testDeleteItem() {
        // Arrange
        Item item = new Item("1", 10.0, "");
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        doNothing().when(itemRepository).deleteById(item.getId());

        // Act and verify
        assertTrue(itemService.delete(item.getId()));
    }

    @Test
    void testGetAll() {
        // Arrange
        Item item = new Item("1", 10.0, "");
        Item item2 = new Item("2", 11.0, "");
        when(itemRepository.findAll()).thenReturn(Arrays.asList(item, item2));
        List<Item> expectedItems = Arrays.asList(item, item2);

        // Act
        List<Item> foundItems = itemService.getAll();

        // Assert
        assertEquals(expectedItems, foundItems);
    }

    @Test
    void testGetById() {
        // Arrange
        Item item = new Item("1", 10.0, "");
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        // Act
        Item foundItem = itemService.getById(item.getId());

        // Assert
        assertEquals(item, foundItem);
    }

    @Test
    void testUpdateItem() {
        // Arrange
        Item item = new Item("1", 10.0, "");
        Item updatedItem = new Item("1", 20.0, "");
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        when(itemRepository.save(updatedItem)).thenReturn(updatedItem);

        // Act
        Item result = itemService.update(updatedItem);

        // Assert
        assertEquals(updatedItem, result);
    }

    @Test
    void testUpdateItem_EntityNotFound() {
        // Arrange
        Item item = new Item("1", 10.0, "");
        when(itemRepository.findById(item.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> itemService.update(item));
        verify(itemRepository, never()).save(any());
    }

    @Test
    void testUpdateItem_UpdateException() {
        // Arrange
        Item item = new Item("1", 10.0, "");
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenThrow(new RuntimeException("Error updating item"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> itemService.update(item));
        verify(itemRepository, times(1)).findById(item.getId());
        verify(itemRepository, times(1)).save(any(Item.class));
    }

}

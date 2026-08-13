package com.family.shopping_list.service;

import com.family.shopping_list.exception.ResourceNotFoundException;
import com.family.shopping_list.model.ShoppingItem;
import com.family.shopping_list.repository.ShoppingItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingItemServiceTest {
    @Mock
    private ShoppingItemRepository repository;

    @InjectMocks
    private ShoppingItemService service;

    @Test
    void toggleIsBought() {
        // Arrange
        ShoppingItem item =  new ShoppingItem(1L, "Pepsi Max", 1, "Gott", false);
        when(repository.findById(item.getId())).thenReturn(Optional.of(item));
        when(repository.save(item)).thenReturn(item);

        // Act
        ShoppingItem result = service.toggleBought(item.getId());

        // Assert
        assertTrue(result.getIsBought());
        verify(repository, times(1)).findById(item.getId());
        verify(repository, times(1)).save(item);
    }

    @Test
    void getItemById_ShouldThrowException_WhenItemDoesNotExist() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.getItemById(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deleteItem_ShouldDelete_WhenItemExists() {
        // Arrange
        ShoppingItem item = new ShoppingItem(1L, "Pepsi Max", 1, "Gott", false);
        when(repository.findById(item.getId())).thenReturn(Optional.of(item));

        // Act
        service.deleteItem(item.getId());

        // Assert
        verify(repository, times(1)).findById(item.getId());
        verify(repository, times(1)).delete(item);
    }
}

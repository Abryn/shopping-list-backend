package com.family.shopping_list.service;

import com.family.shopping_list.exception.ResourceNotFoundException;
import com.family.shopping_list.model.ShoppingItem;
import com.family.shopping_list.repository.ShoppingItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingItemService {
    private final ShoppingItemRepository repository;

    public List<ShoppingItem> getAllItems() {
        return repository.findAll();
    }

    public ShoppingItem createItem(ShoppingItem item) {
        return repository.save(item);
    }

    public ShoppingItem getItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public void deleteItem(Long id) {
        ShoppingItem item = getItemById(id);
        repository.delete(item);
    }

    public ShoppingItem toggleBought(Long id) {
        ShoppingItem item = getItemById(id);
        item.setIsBought(!item.getIsBought());
        return repository.save(item);
    }
}

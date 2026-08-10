package com.family.shopping_list.service;

import com.family.shopping_list.model.ShoppingItem;
import com.family.shopping_list.repository.ShoppingItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<ShoppingItem> getItemById(Long id) {
        return repository.findById(id);
    }

    public void deleteItem(long id) {
        repository.deleteById(id);
    }

    public ShoppingItem toggleBought(long id) {
        ShoppingItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping item not found with id: " + id));
        item.setIsBought(!item.getIsBought());
        return repository.save(item);
    }
}

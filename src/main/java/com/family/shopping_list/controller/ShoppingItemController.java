package com.family.shopping_list.controller;

import com.family.shopping_list.model.ShoppingItem;
import com.family.shopping_list.service.ShoppingItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor

public class ShoppingItemController {
    private final ShoppingItemService service;

    @GetMapping
    public List<ShoppingItem> getAllItems() {
        return service.getAllItems();
    }

    @PostMapping
    public ResponseEntity<ShoppingItem> createItem(@RequestBody ShoppingItem item) {
        ShoppingItem created = service.createItem(item);
        return new ResponseEntity<>(created,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingItem> getItemById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<ShoppingItem> toggleBought(@PathVariable Long id) {
        return ResponseEntity.ok(service.toggleBought(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}

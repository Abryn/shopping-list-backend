package com.family.shopping_list.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping_items")
@Data
@NoArgsConstructor

public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private Integer quantity;
    private String category;
    private Boolean isBought = false;
}

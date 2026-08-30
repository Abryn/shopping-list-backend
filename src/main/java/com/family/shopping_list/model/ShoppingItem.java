package com.family.shopping_list.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "shopping_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product title can't be empty")
    private String title;

    @NotNull(message = "Quantity must be given")
    @Min(value = 1, message = "Quantity must at least be 1")
    private Integer quantity;

    private String category;

    private Boolean isBought = false;
}

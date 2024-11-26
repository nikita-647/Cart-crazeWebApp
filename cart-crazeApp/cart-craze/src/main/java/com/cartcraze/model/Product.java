package com.cartcraze.model;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false)
    // @NotBlank
    @Size(max = 100)
    private String name;

    @PositiveOrZero
    private double price;

    // @NotBlank
    @Size(max = 500)
    private String description;

    private String image;

    @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    private CategoryEnum category;

    @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    private CompanyEnum company;

    private String color;

    private boolean featured;

    private boolean freeShipping;

    @PositiveOrZero
    private int inventory;

}

package com.example.travelseeker.model.entities;

import com.example.travelseeker.model.enums.CategoryEnum;
import jakarta.persistence.*;


@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column
    private String description;

    public Category() {
    }

    public Category(CategoryEnum category, String description) {
        this.category = category;
        this.description = description;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public Category setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}

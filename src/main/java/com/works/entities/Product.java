package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(message = "The title must be min 3 characters and max 40 characters!", min = 3, max = 40)
    private String title;

    private String detail;

    @Min(message = "The price must be min 10!", value = 10)
    @Column(nullable = false)
    private double price;
}

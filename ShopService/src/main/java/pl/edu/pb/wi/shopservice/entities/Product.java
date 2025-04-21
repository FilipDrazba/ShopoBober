package pl.edu.pb.wi.shopservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;
}

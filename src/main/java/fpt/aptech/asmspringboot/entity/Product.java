package fpt.aptech.asmspringboot.entity;

import fpt.aptech.asmspringboot.entity.base.BaseEntity;
import fpt.aptech.asmspringboot.entity.enums.ProductStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Inheritance
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Thumbnail cannot be empty")
    private String thumbnail;
    private double unitPrice;
    private int quantity;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String details;
    private ProductStatus status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}



package fpt.aptech.asmspringboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fpt.aptech.asmspringboot.entity.base.BaseEntity;
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
@Table(name = "order_details")
@Inheritance
public class OrderDetail extends BaseEntity {
    @EmbeddedId
    private OrderDetailId id;
    @NotEmpty(message = "Product name cannot be empty")
    private String productName;
    @NotEmpty(message = "Product name cannot be empty")
    private String thumbnail;
    private double unitPrice;
    private int quantity;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}

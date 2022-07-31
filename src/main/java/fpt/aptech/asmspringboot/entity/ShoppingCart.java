package fpt.aptech.asmspringboot.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    private String id;
    private String shippingName;
    private String shippingPhone;
    private String shippingEmail;
    private String shippingAddress;
    private String shippingNote;
    private long userId;
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    public void addTotalPrice(CartItem cartItem) {
        if(this.totalPrice == null) {
            this.totalPrice = new BigDecimal(0);
        }
        BigDecimal quantityInBigDecimal = new BigDecimal(cartItem.getQuantity());
        this.totalPrice.add(cartItem.getUnitPrice().multiply(quantityInBigDecimal));
    }
}

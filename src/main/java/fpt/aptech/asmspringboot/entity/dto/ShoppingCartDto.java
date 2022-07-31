package fpt.aptech.asmspringboot.entity.dto;

import fpt.aptech.asmspringboot.entity.ShoppingCart;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private String id;
    @NotEmpty
    private long userId;
    private BigDecimal totalPrice;
    @NotEmpty
    private String shippingName;
    @NotEmpty
    private String shippingEmail;
    @NotEmpty
    private String shippingAddress;
    @NotEmpty
    private String shippingPhone;
    private String shippingNote;
    @NotEmpty
    private Set<CartItemDto> cartItems;

    public ShoppingCart generateCart() {
        return ShoppingCart.builder()
                .userId(userId)
                .shippingName(shippingName)
                .shippingEmail(shippingEmail)
                .shippingPhone(shippingPhone)
                .shippingNote(shippingNote)
                .shippingAddress(shippingAddress)
                .build();
    }
}
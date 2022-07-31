package fpt.aptech.asmspringboot.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private long productId;
    private int quantity;
}

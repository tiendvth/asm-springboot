package fpt.aptech.asmspringboot.service;

import fpt.aptech.asmspringboot.entity.CartItem;
import fpt.aptech.asmspringboot.entity.Product;
import fpt.aptech.asmspringboot.entity.ShoppingCart;
import fpt.aptech.asmspringboot.entity.dto.CartItemDto;
import fpt.aptech.asmspringboot.entity.dto.ShoppingCartDto;
import fpt.aptech.asmspringboot.repository.ProductRepository;
import fpt.aptech.asmspringboot.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    final ShoppingCartRepository shoppingCartRepository;
    final ProductRepository productRepository;

    public ShoppingCart save(ShoppingCartDto shoppingCartDto) {
        boolean hasException = false;
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .id(UUID.randomUUID().toString())
                .shippingName(shoppingCartDto.getShippingName())
                .shippingPhone(shoppingCartDto.getShippingPhone())
                .shippingEmail(shoppingCartDto.getShippingEmail())
                .shippingAddress(shoppingCartDto.getShippingAddress())
                .shippingNote(shoppingCartDto.getShippingNote())
                .build();
        Set<CartItem> cartItems = new HashSet<>();
        for(CartItemDto cartItemDto : shoppingCartDto.getCartItems()) {
            Optional<Product> optionalProduct = productRepository.findById(cartItemDto.getProductId());
            if(optionalProduct.isPresent()) {
                hasException = true;
                break;
            }
            Product product = optionalProduct.get();
            CartItem cartItem = CartItem.builder()
                    .productName(product.getName())
                    .productThumbnail(product.getThumbnail())
                    .unitPrice(new BigDecimal(product.getUnitPrice()))
                    .quantity(cartItemDto.getQuantity())
                    .build();
            shoppingCart.addTotalPrice(cartItem);
            cartItems.add(cartItem);
        }
        shoppingCart.setCartItems(cartItems);
        return shoppingCartRepository.save(shoppingCart);
    }
}

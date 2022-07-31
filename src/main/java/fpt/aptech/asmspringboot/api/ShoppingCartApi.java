package fpt.aptech.asmspringboot.api;

import fpt.aptech.asmspringboot.entity.dto.ShoppingCartDto;
import fpt.aptech.asmspringboot.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/shopping-carts")
@RequiredArgsConstructor
public class ShoppingCartApi {
    final ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<?> saveCart(
            @Valid @RequestBody ShoppingCartDto shoppingCartDto
    ) {
        return ResponseEntity.ok(shoppingCartService.save(shoppingCartDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartDetails(
            @PathVariable long userId
    ) {
        return ResponseEntity.ok(1);
    }
}

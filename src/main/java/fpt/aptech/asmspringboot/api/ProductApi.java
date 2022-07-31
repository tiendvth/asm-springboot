package fpt.aptech.asmspringboot.api;

import fpt.aptech.asmspringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductApi {
    final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "21") Integer limit,
                                     @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(productService.findAll(page, limit, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok(productService.findById(id));
    }
}


package fpt.aptech.asmspringboot.api;

import fpt.aptech.asmspringboot.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderApi {
    final OrderService orderService;


}

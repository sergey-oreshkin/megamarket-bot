package group.megamarket.marketservice.controller;


import group.megamarket.marketservice.dto.OrderRequest;
import group.megamarket.marketservice.dto.OrderResponse;
import group.megamarket.marketservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class OrderController {

    private final OrderService service;

    @PutMapping
    public OrderResponse addProduct(@RequestBody OrderRequest orderRequest){
         return service.addProduct(orderRequest);
    }

}

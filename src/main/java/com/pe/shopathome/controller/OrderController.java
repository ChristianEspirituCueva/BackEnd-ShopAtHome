package com.pe.shopathome.controller;

import com.pe.shopathome.converters.OrderConverter;
import com.pe.shopathome.dto.OrderDTO;
import com.pe.shopathome.entity.Order;
import com.pe.shopathome.services.OrderService;
import com.pe.shopathome.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderConverter converter;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<WrapperResponse<List<OrderDTO>>> findAll(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ){

        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Order> orders = orderService.findAll(page);
        return new WrapperResponse<>(true, "success", converter.fromEntity(orders))
                .createResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable(name="id") Long id){
        Order order = orderService.findById(id);
        return new WrapperResponse<>(true, "success", converter.fromEntity(order))
                .createResponse();
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<OrderDTO>> create(@RequestBody OrderDTO order){
        Order newOrder = orderService.save(converter.fromDTO(order));
        return new WrapperResponse<>(true, "success", converter.fromEntity(newOrder))
                .createResponse();
    }

    @PutMapping
    public ResponseEntity<WrapperResponse<OrderDTO>> update(@RequestBody OrderDTO order){
        Order newOrder = orderService.save(converter.fromDTO(order));
        return new WrapperResponse<>(true, "success", converter.fromEntity(newOrder))
                .createResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
        orderService.delete(id);
        return new WrapperResponse<>(true, "success", null).createResponse();
    }
}
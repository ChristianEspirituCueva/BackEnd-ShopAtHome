package com.pe.shopathome.services;

import java.time.LocalDateTime;
import java.util.List;

import com.pe.shopathome.entity.Order;
import com.pe.shopathome.entity.OrderLine;
import com.pe.shopathome.entity.Product;
import com.pe.shopathome.entity.User;
import com.pe.shopathome.exceptions.GeneralServiceException;
import com.pe.shopathome.exceptions.NoDataFoundException;
import com.pe.shopathome.exceptions.ValidateServiceException;
import com.pe.shopathome.repository.OrderLineRepository;
import com.pe.shopathome.repository.OrderRepository;
import com.pe.shopathome.repository.ProductRepository;
import com.pe.shopathome.security.UserPrincipal;
import com.pe.shopathome.validators.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderLineRepository orderLineRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<Order> findAll(Pageable page){
        try {
            return orderRepo.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public Order findById(Long id) {
        try {
            return orderRepo.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public void delete(Long id) {
        try {
            Order order = orderRepo.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));

            orderRepo.delete(order);

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public Order save(Order order) {
        try {
            OrderValidator.save(order);

            //User user= UserPrincipal.getCurrentUser();

            double total = 0;
            for(OrderLine line : order.getLines()) {
                Product product = productRepo.findById(line.getProduct().getId())
                        .orElseThrow(() -> new NoDataFoundException("No existe el producto " + line.getProduct().getId()));

                line.setPrice(product.getPrice());
                line.setTotal(product.getPrice() * line.getQuantity());
                total += line.getTotal();
            }
            order.setTotal(total);
            order.getLines().forEach(line -> line.setOrder(order));

            //Create Order
            if(order.getId() == null) {
                //order.setUser(user);
                order.setRegDate(LocalDateTime.now());
                return orderRepo.save(order);
            }

            //Update Order
            Order savedOrder = orderRepo.findById(order.getId())
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
            //RegDate no se cambia, se mantiene la de creacion
            order.setRegDate(savedOrder.getRegDate());

            List<OrderLine> deletedLines = savedOrder.getLines();//Obtiene las lineas asociadas a la order obtenida
            deletedLines.removeAll(order.getLines());//Elimina las lineas asociadas a la orden
            orderLineRepo.deleteAll(deletedLines);

            return orderRepo.save(order);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

}
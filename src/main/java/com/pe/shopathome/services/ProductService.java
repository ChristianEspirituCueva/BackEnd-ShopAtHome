package com.pe.shopathome.services;

import com.pe.shopathome.validators.ProductValidator;
import com.pe.shopathome.entity.Product;
import com.pe.shopathome.exceptions.GeneralServiceException;
import com.pe.shopathome.exceptions.NoDataFoundException;
import com.pe.shopathome.exceptions.ValidateServiceException;
import com.pe.shopathome.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepo;


    public Product findById(Long productId) {
        try {
            log.debug("findById => " + productId);
            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            return product;
        } catch(ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public void delete(Long productId) {
        try {
            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            productRepo.delete(product);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    public List<Product> findAll(Pageable page){
        try {
            List<Product> products = productRepo.findAll(page).toList();
            return products;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public List<Product> findAll(){
        try {
            List<Product> products = productRepo.findAll();
            return products;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public Product save(Product product) {
        try {
            ProductValidator.save(product);

            if(product.getId() == null) {
                Product newProduct = productRepo.save(product);
                return newProduct;
            }

            Product exitProduct = productRepo.findById(product.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe el producto"));

            exitProduct.setName(product.getName());
            exitProduct.setPrice(product.getPrice());

            productRepo.save(exitProduct);

            return exitProduct;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }
}
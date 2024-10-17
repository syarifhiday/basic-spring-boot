package com.techno.basicspringboot.service.impl;

import com.techno.basicspringboot.dto.response.BaseResponseDto;
import com.techno.basicspringboot.dto.response.ProductResponseDto;
import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.repository.ProductRepository;
import com.techno.basicspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Optional<Product> getOne(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
//        return List.of(productRepository.findAll().get());
    }


    @Override
    public String save(Product product) {
        try{
            productRepository.save(product);
            return "success to create product";
        }catch (HttpClientErrorException e){
            e.printStackTrace();
            return "failed to create product";
        }
    }

    @Override
    public String update(Long id, Product product) {
        try{
            Product product1 = productRepository.getById(id);

            product1.setProductName(product.getProductName());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());
            productRepository.save(product1);
            return "success to update product";
        }catch (HttpClientErrorException e){
            e.printStackTrace();
            return "failed to update product";
        }
    }

    @Override
    public String delete(Long id) {
        try{
            Product productToDeleted = productRepository.getById(id);
            productRepository.delete(productToDeleted);
            return "success to delete";
        }catch (HttpClientErrorException e){
            e.printStackTrace();
            return "failed to delete";
        }
    }
}

package com.example.springmongo.service;

import com.example.springmongo.model.Product;
import com.example.springmongo.repository.ProductRepository;
import com.example.springmongo.util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSvcImp implements ProductSvc {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new DataNotFoundException("Not Found");
        return product;
    }

    @Override
    public Product update(String id, Product product) {
        Product productById = this.findById(id);
        productById.setName(product.getName());
        productById.setPrice(product.getPrice());
        productById.setQty(product.getQty());
        return productRepository.save(productById);
    }

    @Override
    public void delete(String id) {
        Product product = this.findById(id);
        productRepository.delete(product);
    }
}

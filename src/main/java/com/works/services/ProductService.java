package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.ERest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity save(Product product){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        productRepository.save(product);
        hm.put(ERest.status, true);
        hm.put(ERest.result, product);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list(){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.result, productRepository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity search(String q){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        List<Product> listedProducts = productRepository.findByTitleContainsIgnoreCaseOrDetailContainsIgnoreCase(q, q);
        hm.put(ERest.totalResult, productRepository.countAllBy());
        hm.put(ERest.searchTotal, listedProducts.size());
        hm.put(ERest.status, true);
        hm.put(ERest.result, listedProducts);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity filterByPrice(double q){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        List<Product> listedProducts = productRepository.findByPriceGreaterThanEqual(q);
        hm.put(ERest.totalResult, productRepository.countAllBy());
        hm.put(ERest.searchTotal, listedProducts.size());
        hm.put(ERest.status, true);
        hm.put(ERest.result, listedProducts);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public  ResponseEntity update(Product product){
        Map<ERest, Object> hm = new LinkedHashMap<>();

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()){
            productRepository.saveAndFlush(product);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Product updated.");
            hm.put(ERest.result, product);
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Product id not found");
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity delete(String id){
        Map<ERest, Object> hm = new LinkedHashMap<>();
        try {
            Long productId = Long.parseLong(id);
            productRepository.deleteById(productId);
            hm.put(ERest.status, true);
            hm.put(ERest.message, "Product deleted.");
        }catch (Exception e){
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Product id not found");
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }
}

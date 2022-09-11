package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    final ProductService productService;
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return productService.list();
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String q){
        return productService.search(q);
    }

    @GetMapping("/filterByPrice")
    public ResponseEntity filterByPrice(double q){
        return productService.filterByPrice(q);
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(String id){
        return productService.delete(id);
    }
}

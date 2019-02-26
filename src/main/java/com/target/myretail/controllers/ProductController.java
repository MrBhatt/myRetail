package com.target.myretail.controllers;

import com.target.myretail.models.Product;
import com.target.myretail.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductDetailService productDetailService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable long id) {
        Product product = productDetailService.getProductDetails(id);
        return product;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product setProduct(@RequestBody Product productPayload) {
        return new Product();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProductById(@RequestBody Product productPayload) {
        return new Product();
    }
}

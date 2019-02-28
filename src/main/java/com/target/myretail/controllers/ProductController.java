package com.target.myretail.controllers;

import com.target.myretail.models.Product;
import com.target.myretail.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductDetailService productDetailService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable long id) {
        return productDetailService.getProductDetails(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product setProduct(@Valid @RequestBody Product productPayload) {
        return productDetailService.createNewProduct(productPayload);
    }
}

package com.target.myretail.controllers;

import com.target.myretail.models.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable long id)
    {
        return new Product();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product setProduct(@RequestBody Product productPayload)
    {
        return new Product();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProductById(@RequestBody Product productPayload)
    {
        return new Product();
    }
}

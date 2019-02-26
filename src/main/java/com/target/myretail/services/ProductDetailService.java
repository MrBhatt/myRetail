package com.target.myretail.services;

import com.target.myretail.integrations.RedSkyService;
import com.target.myretail.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {

    @Autowired
    private RedSkyService redSkyService;

    public Product getProductDetails(long id) {

        // #step 1: call the redSky service to fetch product details and extract product name
        return null;
    }
}

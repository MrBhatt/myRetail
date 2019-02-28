package com.target.myretail.services;

import com.target.myretail.models.dto.Product;

public interface ProductDetailService {

    Product getProductDetails(long id);

    Product createNewProduct(Product productPayload);

}

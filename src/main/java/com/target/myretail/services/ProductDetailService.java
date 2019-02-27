package com.target.myretail.services;

import com.target.myretail.integrations.PricingService;
import com.target.myretail.integrations.RedSkyService;
import com.target.myretail.models.Price;
import com.target.myretail.models.Product;
import com.target.myretail.models.ProductPriceDetail;
import com.target.myretail.models.redsky.RedSkyProduct;
import com.target.myretail.models.redsky.RedSkyProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailService {

    @Autowired
    private RedSkyService redSkyService;

    @Autowired
    private PricingService pricingService;

    public Product getProductDetails(long id) {

        Product product = new Product();

        // # step 1: call the redSky service to fetch product details and extract product name
        RedSkyProductDetails redSkyProductDetails = redSkyService.getProductDetails(id);

        RedSkyProduct redSkyProduct = redSkyProductDetails.getRedSkyProduct();

        String title = redSkyProduct.getRedSkyItem().getProductDescription().getTitle();

        // # step 2: call the pricing service to fetch product price
        ProductPriceDetail productPriceDetail = pricingService.getProductPricing(id);
        Price price = productPriceDetail.getPrice();

        // populate product instance with relevant details
        product.setId(id);
        product.setName(title);
        product.setCurrentPrice(price);

        return product;
    }
}

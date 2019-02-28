package com.target.myretail.services;

import com.target.myretail.exceptions.ServiceNotAvailableException;
import com.target.myretail.integrations.PricingService;
import com.target.myretail.integrations.RedSkyService;
import com.target.myretail.models.Price;
import com.target.myretail.models.Product;
import com.target.myretail.models.ProductPriceDetail;
import com.target.myretail.models.redsky.RedSkyProduct;
import com.target.myretail.models.redsky.RedSkyProductDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private RedSkyService redSkyService;

    @Autowired
    private PricingService pricingService;

    public Product getProductDetails(long id) {

        Product product = new Product();

        log.info("Getting product details for product id: {} from redsky", id);

        // # step 1: call the redSky service to fetch product details and extract product name
        RedSkyProductDetails redSkyProductDetails = redSkyService.getProductDetails(id);

        RedSkyProduct redSkyProduct = redSkyProductDetails.getRedSkyProduct();

        // handle situation where the request was successful but complete data was found
        validateRedSkyResponse(redSkyProduct);

        String title = redSkyProduct.getRedSkyItem().getProductDescription().getTitle();

        // # step 2: call the pricing service to fetch product price
        log.info("Getting pricing details for product id: {} from pricing API", id);

        ProductPriceDetail productPriceDetail = pricingService.getProductPricing(id);

        // handle situation where the request was successful but complete data was found
        validatePricingResponse(productPriceDetail);

        Price price = productPriceDetail.getPrice();

        // populate product instance with relevant details
        product.setId(id);
        product.setName(title);
        product.setCurrentPrice(price);

        log.info("Complete product JSON: {}", product);

        return product;
    }

    private void validateRedSkyResponse(RedSkyProduct redSkyProduct) {
        if (Objects.isNull(redSkyProduct)
                || Objects.isNull(redSkyProduct.getRedSkyItem())
                || Objects.isNull(redSkyProduct.getRedSkyItem().getProductDescription())) {

            log.warn("incomplete data recieved from redsky {}", redSkyProduct);
            throw new ServiceNotAvailableException("Unexpected response from Service");
        }
    }

    private void validatePricingResponse(ProductPriceDetail productPriceDetail) {
        if (Objects.isNull(productPriceDetail)
                || Objects.isNull(productPriceDetail.getPrice())) {

            log.warn("incomplete data recieved from pricing service {}", productPriceDetail);
            throw new ServiceNotAvailableException("Unexpected response from Service");
        }
    }
}

package com.target.myretail.integrations;

import com.target.myretail.models.ProductPriceDetail;

public interface PricingService {
    ProductPriceDetail getProductPricing(long id);
}

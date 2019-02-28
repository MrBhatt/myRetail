package com.target.myretail.integrations;

import com.target.myretail.models.Product;
import com.target.myretail.models.redsky.RedSkyProductDetails;

public interface RedSkyService {

    RedSkyProductDetails getProductDetails(long id);

    RedSkyProductDetails createProduct(Product productPayload);
}

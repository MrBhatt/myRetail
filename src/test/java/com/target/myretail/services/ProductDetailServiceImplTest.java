package com.target.myretail.services;

import com.target.myretail.integrations.PricingService;
import com.target.myretail.integrations.RedSkyService;
import com.target.myretail.models.Price;
import com.target.myretail.models.Product;
import com.target.myretail.models.ProductPriceDetail;
import com.target.myretail.models.redsky.RedSkyItem;
import com.target.myretail.models.redsky.RedSkyProduct;
import com.target.myretail.models.redsky.RedSkyProductDescription;
import com.target.myretail.models.redsky.RedSkyProductDetails;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDetailServiceImplTest {

    @MockBean
    private RedSkyService redSkyService;

    @MockBean
    private PricingService pricingService;

    @Autowired
    private ProductDetailService productDetailService;

    private static RedSkyProductDetails redSkyProductDetails;
    private static ProductPriceDetail productPriceDetail;

    private static final long VALID_ID = 138604281;
    private static final long INVALID_ID = 999999;

    @BeforeClass
    public static void setUp() {
        RedSkyItem redSkyItem = new RedSkyItem();
        redSkyItem.setTcin(VALID_ID);
        RedSkyProductDescription redSkyProductDescription = new RedSkyProductDescription();
        redSkyProductDescription.setTitle("The Big Lebowski (Blu-ray)");
        redSkyItem.setProductDescription(redSkyProductDescription);

        RedSkyProduct redSkyProduct = new RedSkyProduct();
        redSkyProduct.setRedSkyItem(redSkyItem);

        redSkyProductDetails = new RedSkyProductDetails();
        redSkyProductDetails.setRedSkyProduct(redSkyProduct);

        productPriceDetail = new ProductPriceDetail();
        productPriceDetail.setId(VALID_ID);
        Price price = new Price();
        price.setValue(19.48);
        price.setCurrencyCode("USD");
        productPriceDetail.setPrice(price);
    }

    @Test
    public void getProductDetails_withValidId_returnsProduct() {
        given(this.redSkyService.getProductDetails(VALID_ID)).willReturn(redSkyProductDetails);
        given(this.pricingService.getProductPricing(VALID_ID)).willReturn(productPriceDetail);

        Product productDetails = productDetailService.getProductDetails(138604281);

        Product productToCompare = new Product();
        productToCompare.setId(VALID_ID);
        productToCompare.setName("The Big Lebowski (Blu-ray)");
        Price expectedPrice = new Price();
        expectedPrice.setValue(19.48);
        expectedPrice.setCurrencyCode("USD");
        productToCompare.setCurrentPrice(expectedPrice);

        assertThat(productDetails).isEqualTo(productToCompare);
    }

    @Test
    public void getProductDetails_withInvalidId_returns404Error() {
        HttpClientErrorException httpClientErrorException = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Resource Not Found", null, null, null);
        given(this.redSkyService.getProductDetails(INVALID_ID)).willThrow(httpClientErrorException);
        given(this.pricingService.getProductPricing(INVALID_ID)).willThrow(httpClientErrorException);

        assertThatExceptionOfType(HttpClientErrorException.class).isThrownBy(() -> {
            productDetailService.getProductDetails(INVALID_ID);
        });
    }
}
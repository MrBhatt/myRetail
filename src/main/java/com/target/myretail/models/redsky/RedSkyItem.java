package com.target.myretail.models.redsky;

public class RedSkyItem {
    private long tcin;
    private RedSkyProductDescription productDescription;

    public long getTcin() {
        return tcin;
    }

    public void setTcin(long tcin) {
        this.tcin = tcin;
    }

    public RedSkyProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(RedSkyProductDescription productDescription) {
        this.productDescription = productDescription;
    }
}

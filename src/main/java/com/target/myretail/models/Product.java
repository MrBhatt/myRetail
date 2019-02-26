package com.target.myretail.models;

public class Product {

    /** product id **/
    private long id;
    private String name;
    private Price currentPrice;

    public long getId() {return id;}
    public String getName() {return name;}
    public Price getCurrentPrice() {return currentPrice;}

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCurrentPrice(Price currentPrice) {this.currentPrice = currentPrice;}

}

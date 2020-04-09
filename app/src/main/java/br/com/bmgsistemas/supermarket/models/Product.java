package br.com.bmgsistemas.supermarket.models;

public class Product {
//    private String image;
    private String barCode;
    private String name;

    public Product(String barCode, String name, String image) {
//        this.image = image;
        this.barCode = barCode;
        this.name = name;
    }
//    public String getImage() {
//        return image;
//    }
    public String getBarCode() {
        return barCode;
    }
    public String getName() {
        return name;
    }
}

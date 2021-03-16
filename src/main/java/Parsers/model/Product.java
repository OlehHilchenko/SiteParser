package Parsers.model;

import java.util.Objects;

public class Product {

    private String oldPriceInfo;

    private String currentPriceInfo;

    private String imageUrl;

    private String productUrl;

    private String description;

    public Product() {
    }

    public Product(String oldPriceInfo, String currentPriceInfo, String imageUrl, String productUrl, String description) {
        this.oldPriceInfo = oldPriceInfo;
        this.currentPriceInfo = currentPriceInfo;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.description = description;
    }

    public String getOldPriceInfo() {
        return oldPriceInfo;
    }

    public void setOldPriceInfo(String oldPriceInfo) {
        this.oldPriceInfo = oldPriceInfo;
    }

    public String getCurrentPriceInfo() {
        return currentPriceInfo;
    }

    public void setCurrentPriceInfo(String currentPriceInfo) {
        this.currentPriceInfo = currentPriceInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(oldPriceInfo, product.oldPriceInfo) &&
                Objects.equals(currentPriceInfo, product.currentPriceInfo) &&
                Objects.equals(imageUrl, product.imageUrl) &&
                Objects.equals(productUrl, product.productUrl) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oldPriceInfo, currentPriceInfo, imageUrl, productUrl, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "oldPriceInfo='" + oldPriceInfo + '\'' +
                ", currentPriceInfo='" + currentPriceInfo + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

package app.ittraing.com.ittrainingdemoapp.POJO;

import java.io.Serializable;

/**
 * Created by ranja_000 on 6/29/2017.
 */

public class MenuList implements Serializable {
    String name, price, details, image, materials;

    public MenuList(String name, String price, String details, String image, String materials) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.image = image;
        this.materials = materials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }
}

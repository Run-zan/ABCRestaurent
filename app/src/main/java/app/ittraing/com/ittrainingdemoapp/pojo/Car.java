package app.ittraing.com.ittrainingdemoapp.pojo;

import java.io.Serializable;

/**
 * Created by ranja_000 on 11/3/2017.
 */

public class Car implements Serializable {
    float price;
    String name, model, color, date;

    public Car(float price, String name, String model, String color, String date) {
        this.price = price;
        this.name = name;
        this.model = model;
        this.color = color;
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

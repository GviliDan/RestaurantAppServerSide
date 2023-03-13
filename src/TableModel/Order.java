package TableModel;

import java.io.Serializable;

public abstract class Order implements Serializable {
    Integer price;
    String name;

    public Order(Integer price,String orderName) {
        this.price = price;
        this.name = orderName;
    }


    public Order() {

    }

    public String getOrderName() {
        return name;
    }

    public void setOrderName(String orderName) {
        this.name = orderName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}

package TableModel;

import java.io.Serializable;

public class Food extends Order implements Serializable {

    public Food(Integer price, String name) {
        super();
        this.price= price;
        this.name= name;
    }

    public Food() {

    }

    public String toString(){
        return "price= "+price+" name= "+name+" " ;
    }

}

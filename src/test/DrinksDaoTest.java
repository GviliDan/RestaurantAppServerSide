package test;

import Dao.DrinksMenuDao;
import Dao.FoodMenuDao;
import TableModel.Drinks;
import TableModel.Food;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DrinksDaoTest {


    @Test
    public void update_GetFullMenuTest() throws IOException, ClassNotFoundException {
        DrinksMenuDao dao = new DrinksMenuDao();
        dao.setPath("DrinksMenuTest.txt");
        Drinks o1 = new Drinks(11, "coffe");
        Drinks o2 = new Drinks(22, "water");
        Drinks o3 = new Drinks(33, "cola");
        Map<Integer, Drinks> myMenu =new HashMap<Integer,Drinks>();
        myMenu.put(1,o1);
        myMenu.put(2,o2);
        myMenu.put(3,o3);

        dao.updateFullDrinksMenu(myMenu);
        Map<Integer, Drinks> menu= dao.getDrinksMenu();

        Assertions.assertEquals(myMenu.get(1).getPrice(),menu.get(1).getPrice());
        Assertions.assertEquals(myMenu.get(1).getOrderName(),menu.get(1).getOrderName());
        Assertions.assertEquals(myMenu.get(2).getPrice(),menu.get(2).getPrice());
        Assertions.assertEquals(myMenu.get(2).getOrderName(),menu.get(2).getOrderName());
        Assertions.assertEquals(myMenu.get(3).getPrice(),menu.get(3).getPrice());
        Assertions.assertEquals(myMenu.get(3).getOrderName(),menu.get(3).getOrderName());
    }

    @Test
    public void  addDrinkToMenu() throws IOException, ClassNotFoundException {
        DrinksMenuDao dao = new DrinksMenuDao();
        dao.setPath("DrinksMenuTest.txt");
        Drinks o1 = new Drinks(11, "coffe");
        Drinks o2 = new Drinks(22, "water");
        Drinks o3 = new Drinks(33, "cola");
        Map<Integer, Drinks> myMenu =new HashMap<Integer,Drinks>();
        myMenu.put(1,o1);
        myMenu.put(2,o2);
        myMenu.put(3,o3);

        dao.addDrinkToMenu(1,o1);
        dao.addDrinkToMenu(2,o2);
        dao.addDrinkToMenu(3,o3);

        Map<Integer, Drinks> menu= dao.getDrinksMenu();
        Assertions.assertEquals(myMenu.get(1).getPrice(),menu.get(1).getPrice());
        Assertions.assertEquals(myMenu.get(1).getOrderName(),menu.get(1).getOrderName());
        Assertions.assertEquals(myMenu.get(2).getPrice(),menu.get(2).getPrice());
        Assertions.assertEquals(myMenu.get(2).getOrderName(),menu.get(2).getOrderName());
        Assertions.assertEquals(myMenu.get(3).getPrice(),menu.get(3).getPrice());
        Assertions.assertEquals(myMenu.get(3).getOrderName(),menu.get(3).getOrderName());
    }

    @Test
    public void  deleteDrinkToMenuTest() throws IOException, ClassNotFoundException {
        FoodMenuDao dao = new FoodMenuDao();
        dao.setPath("DrinksMenuTest.txt");

        dao.deleteFoodFromMenuById(1);

        Map<Integer, Food> menu= dao.getFoodMenu();
        Assertions.assertEquals(null,menu.get(1));
        Assertions.assertEquals(22,menu.get(2).getPrice());
        Assertions.assertEquals("water",menu.get(2).getOrderName());
        Assertions.assertEquals(33,menu.get(3).getPrice());
        Assertions.assertEquals("cola",menu.get(3).getOrderName());

    }
}

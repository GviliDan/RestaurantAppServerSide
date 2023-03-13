package test;

import Dao.FoodMenuDao;
import TableModel.Food;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FoodDaoTest {


    @Test
    public void update_GetFullMenuTest() throws IOException, ClassNotFoundException {
        FoodMenuDao dao = new FoodMenuDao();
        dao.setPath("FoodMenuTest.txt");
        Food o1 = new Food(11, "burger");
        Food o2 = new Food(22, "pita");
        Food o3 = new Food(33, "pizza");
        Map<Integer, Food> myMenu =new HashMap<Integer,Food>();
        myMenu.put(1,o1);
        myMenu.put(2,o2);
        myMenu.put(3,o3);

        dao.updateFullFoodMenu(myMenu);
        Map<Integer, Food> menu= dao.getFoodMenu();

        Assertions.assertEquals(myMenu.get(1).getPrice(),menu.get(1).getPrice());
        Assertions.assertEquals(myMenu.get(1).getOrderName(),menu.get(1).getOrderName());
        Assertions.assertEquals(myMenu.get(2).getPrice(),menu.get(2).getPrice());
        Assertions.assertEquals(myMenu.get(2).getOrderName(),menu.get(2).getOrderName());
        Assertions.assertEquals(myMenu.get(3).getPrice(),menu.get(3).getPrice());
        Assertions.assertEquals(myMenu.get(3).getOrderName(),menu.get(3).getOrderName());

        Assertions.assertEquals(myMenu.values().toString(),menu.values().toString());

    }



    @Test
    public void  addFoodToMenuTest() throws IOException, ClassNotFoundException {
        FoodMenuDao dao = new FoodMenuDao();
        dao.setPath("FoodMenuTest.txt");
        Food o1 = new Food(11, "burger");
        Food o2 = new Food(22, "pita");
        Food o3 = new Food(33, "pizza");
        Map<Integer, Food> myMenu =new HashMap<Integer,Food>();
        myMenu.put(1,o1);
        myMenu.put(2,o2);
        myMenu.put(3,o3);

        dao.addFoodToMenu(1,o1);
        dao.addFoodToMenu(2,o2);
        dao.addFoodToMenu(3,o3);

        Map<Integer, Food> menu= dao.getFoodMenu();
        Assertions.assertEquals(myMenu.get(1).getPrice(),menu.get(1).getPrice());
        Assertions.assertEquals(myMenu.get(1).getOrderName(),menu.get(1).getOrderName());
        Assertions.assertEquals(myMenu.get(2).getPrice(),menu.get(2).getPrice());
        Assertions.assertEquals(myMenu.get(2).getOrderName(),menu.get(2).getOrderName());
        Assertions.assertEquals(myMenu.get(3).getPrice(),menu.get(3).getPrice());
        Assertions.assertEquals(myMenu.get(3).getOrderName(),menu.get(3).getOrderName());

        Assertions.assertEquals(myMenu.values().toString(),menu.values().toString());
    }

    @Test
    public void  deleteFoodToMenuTest() throws IOException, ClassNotFoundException {
        FoodMenuDao dao = new FoodMenuDao();
        dao.setPath("FoodMenuTest.txt");

        dao.deleteFoodFromMenuById(1);

        Map<Integer, Food> menu= dao.getFoodMenu();
        Assertions.assertEquals(null,menu.get(1));
        Assertions.assertEquals(22,menu.get(2).getPrice());
        Assertions.assertEquals("pita",menu.get(2).getOrderName());
        Assertions.assertEquals(33,menu.get(3).getPrice());
        Assertions.assertEquals("pizza",menu.get(3).getOrderName());

    }
}

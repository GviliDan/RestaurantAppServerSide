package Dao;

import TableModel.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class FoodMenuDao {

    public String path = "FoodMenu.txt";

    FileWriter fileWriter;
    BufferedReader reader;

    public void setPath(String path) {
        this.path = path;
    }

    public FoodMenuDao() throws IOException {
        fileWriter = new FileWriter(path, true);
        reader= new BufferedReader(new FileReader(path));
        fileWriter.close();
        reader.close();

    }


    public Map<Integer,Food> getFoodMenu() throws IOException, ClassNotFoundException {
        reader= new BufferedReader(new FileReader(path));
        Gson gson=new Gson();
        Type type = new TypeToken<HashMap<Integer, Food>>(){}.getType();
        Map<Integer,Food> menu=null;
        try{
            String menuStr= reader.readLine();

            menu= (Map<Integer, Food>) gson.fromJson(menuStr, type);
        } catch (IOException e){
            e.printStackTrace();
        }
        reader.close();
        return menu;
    }

    public void updateFullFoodMenu(Map<Integer,Food> menu) throws IOException {
        fileWriter = new FileWriter(path, false);
        Gson gson=new Gson();
        Type type = new TypeToken<HashMap<Integer, Food>>(){}.getType();
        try {
            fileWriter.write(gson.toJson(menu));

        }  catch (IOException e){
            e.printStackTrace();
        }
        fileWriter.close();
    }

    public void addFoodToMenu(Integer key,Food food) throws IOException, ClassNotFoundException {
        reader= new BufferedReader(new FileReader(path));
        fileWriter = new FileWriter(path, true);
        Map<Integer, Food> list = null;
        list = getFoodMenu();
        list.put(key,food);
        updateFullFoodMenu(list);
    }

    public void deleteFoodFromMenuById(Integer key) throws IOException, ClassNotFoundException{
        Map<Integer, Food> list = getFoodMenu();
        list.remove(key);
        updateFullFoodMenu(list);
    }
}

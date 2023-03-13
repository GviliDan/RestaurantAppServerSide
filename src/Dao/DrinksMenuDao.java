package Dao;

import TableModel.Drinks;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DrinksMenuDao {

    public String path = "DrinksMenu.txt";

    FileWriter fileWriter;
    BufferedReader reader;

    public void setPath(String path) {
        this.path = path;
    }

    public DrinksMenuDao() throws IOException {
        fileWriter = new FileWriter(path, true);
        reader= new BufferedReader(new FileReader(path));
        fileWriter.close();
        reader.close();

    }


    public Map<Integer,Drinks> getDrinksMenu() throws IOException, ClassNotFoundException {
        reader= new BufferedReader(new FileReader(path));
        Gson gson=new Gson();
        Type type = new TypeToken<HashMap<Integer, Drinks>>(){}.getType();
        Map<Integer,Drinks> menu=null;
        try{
            String menuStr= reader.readLine();

            menu= (Map<Integer, Drinks>) gson.fromJson(menuStr, type);
        } catch (IOException e){
            e.printStackTrace();
        }
        reader.close();
        return menu;
    }

    public void updateFullDrinksMenu(Map<Integer,Drinks> menu) throws IOException {
        fileWriter = new FileWriter(path, false);
        Gson gson=new Gson();
        Type type = new TypeToken<HashMap<Integer, Drinks>>(){}.getType();
        try {
            fileWriter.write(gson.toJson(menu));

        }  catch (IOException e){
            e.printStackTrace();
        }
        fileWriter.close();
    }

    public void addDrinkToMenu(Integer key,Drinks drink) throws IOException, ClassNotFoundException {
        reader= new BufferedReader(new FileReader(path));
        fileWriter = new FileWriter(path, true);
        Map<Integer, Drinks> list = null;
        list = getDrinksMenu();
        list.put(key,drink);
        updateFullDrinksMenu(list);
    }

    public void deleteDrinkFromMenuById(Integer key) throws IOException, ClassNotFoundException{
        Map<Integer, Drinks> list = getDrinksMenu();
        list.remove(key);
        updateFullDrinksMenu(list);
    }
}

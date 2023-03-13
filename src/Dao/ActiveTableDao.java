package Dao;

import TableModel.LocalDateTimeAdapter;
import TableModel.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ActiveTableDao {

    public String path = "activeTables.txt";

    FileWriter fileWriter;
    BufferedReader reader;


    public void setPath(String path) {
        this.path = path;
    }

    public ActiveTableDao() throws IOException{

        fileWriter = new FileWriter(path, true);
        reader= new BufferedReader(new FileReader(path));
        fileWriter.close();
        reader.close();
    }

    public ArrayList<Table> refreshActiveTables() throws IOException{
        reader= new BufferedReader(new FileReader(path));
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        Type type = new TypeToken<ArrayList<Table>>() {}.getType();
        ArrayList<Table> myTables=null;
        try{
            String myTablesStr= reader.readLine();

            myTables= (ArrayList<Table>) gson.fromJson(myTablesStr, type);
        } catch (IOException e){
            e.printStackTrace();
        }
        reader.close();
        return myTables;
    }

    public void updateFullActiveTables(ArrayList<Table> activeTables) throws IOException, ClassNotFoundException {
        fileWriter = new FileWriter(path, false);
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        Type type = new TypeToken<ArrayList<Table>>() {}.getType();
        try {
            fileWriter.write(gson.toJson(activeTables));

        }  catch (IOException e){
            e.printStackTrace();
        }
        fileWriter.close();
    }


    public void updateTable(Integer tableNum, Table table) throws IOException, ClassNotFoundException {
        reader= new BufferedReader(new FileReader(path));
        fileWriter = new FileWriter(path, true);
        ArrayList<Table> myTables= null;
        myTables = refreshActiveTables();
        myTables.set(tableNum-1, table);
        updateFullActiveTables(myTables);
    }

    public void closeTable(Integer tableNum) throws IOException, ClassNotFoundException{
        ArrayList<Table> myTables= refreshActiveTables();
        myTables.set(tableNum, new Table(0,tableNum, false));
        updateFullActiveTables(myTables);
    }



}

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class TableHistoryDao {

    public String path = "historyTables.txt";

    FileWriter fileWriter;
    BufferedReader reader;


    public void setPath(String path) {
        this.path = path;
    }

    public TableHistoryDao() throws IOException{

        fileWriter = new FileWriter(path, true);
        reader= new BufferedReader(new FileReader(path));
        fileWriter.close();
        reader.close();
    }

    public ArrayList<Table> getFullTableHistory() throws IOException{
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

    public ArrayList<Table> getDailyTableHistory() throws IOException{
        ArrayList<Table> myTables=getFullTableHistory();

        for (int i=0; i<myTables.size();i++){
            if(myTables.get(i).getTableOpenTime().isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)))){
                myTables.remove(i);
                i--;
            }
        }
        return myTables;
    }

    public void updateFullHistoryTables(ArrayList<Table> activeTables) throws IOException, ClassNotFoundException {
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




    public void addTableToHistory(Table table) throws IOException, ClassNotFoundException {
        reader= new BufferedReader(new FileReader(path));
        fileWriter = new FileWriter(path, true);
        ArrayList<Table> myTables= null;
        myTables = getFullTableHistory();
        myTables.add(table);
        updateFullHistoryTables(myTables);
    }

    public Integer calculateIncome() throws IOException {
        Integer income=0;
        ArrayList<Table> myTables= getFullTableHistory();
        for (Table table: myTables) {
            income+= table.getPayment().getTotal_amount();
        }
        return income;
    }

    public Integer calculateIncomeByMonth(Integer month, Integer year) throws IOException {
        Integer income=0;
        ArrayList<Table> myTables= getFullTableHistory();
        for (Table table: myTables) {
            if ((table.getTableOpenTime().getMonthValue() == month)&& (table.getTableOpenTime().getYear()==year )){
                income+= table.getPayment().getTotal_amount();
            }
        }
        return income;
    }

    public Integer calculateIncomeByDay(Integer day, Integer month, Integer year) throws IOException {
        Integer income=0;
        ArrayList<Table> myTables= getFullTableHistory();
        for (Table table: myTables) {
            if ((table.getTableOpenTime().getDayOfMonth() == day)&&(table.getTableOpenTime().getMonthValue() == month)&& (table.getTableOpenTime().getYear()==year )){
                income+= table.getPayment().getTotal_amount();
            }
        }
        return income;
    }


    public Integer calculateTipByDay(Integer day, Integer month, Integer year) throws IOException {
        Integer tip=0;
        ArrayList<Table> myTables= getFullTableHistory();
        for (Table table: myTables) {
            if ((table.getTableOpenTime().getDayOfMonth() == day)&&(table.getTableOpenTime().getMonthValue() == month)&& (table.getTableOpenTime().getYear()==year )){
                tip+= table.getPayment().getTip();
            }
        }
        return tip;
    }



}

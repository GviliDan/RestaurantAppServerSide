package Server;

import Dao.ActiveTableDao;
import Dao.DrinksMenuDao;
import Dao.FoodMenuDao;
import Dao.TableHistoryDao;
import TableModel.Drinks;
import TableModel.Food;
import TableModel.LocalDateTimeAdapter;
import TableModel.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import searchAlgo.ISearch;
import searchAlgo.kmpAlgo;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Route implements Runnable {

    private Socket someClient;

    public Route(Socket someClient) {
        this.someClient = someClient;

    }

    @Override
    public void run() {
        try {

//            ObjectOutputStream outputStream = new ObjectOutputStream(someClient.getOutputStream());
//            ObjectInputStream inputStream = new ObjectInputStream(someClient.getInputStream());

            BufferedReader in = new BufferedReader(new InputStreamReader(someClient.getInputStream()));
            PrintWriter out = new PrintWriter(someClient.getOutputStream(), true);

            out.println("you are conected to the server");
            out.flush();

            String messageIn = (String) in.readLine();
            System.out.println("message from client: " + messageIn);
            Gson gson = new Gson();
            Gson gson1 = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            Request request = gson.fromJson(messageIn, Request.class);
            FoodMenuDao foodDao = new FoodMenuDao();
            DrinksMenuDao drinkDao = new DrinksMenuDao();
            ActiveTableDao tableDao = new ActiveTableDao();
            TableHistoryDao historyDao = new TableHistoryDao();

            switch (request.getAction()) {
                case "bring food menu": {
                    Map<Integer, Food> menu = foodDao.getFoodMenu();
                    String messageToClient = gson.toJson(menu);
                    out.println(messageToClient);
                    out.flush();
                    break;
                }
                case "insert food": {
                    Food food = gson.fromJson(request.getData(), Food.class);
                    foodDao.addFoodToMenu(Integer.parseInt(request.getName()), food);
//                    foodDao.addFoodToMenu(5,food);
                    System.out.println(food.toString());
                    System.out.println(foodDao.getFoodMenu().toString());
                    out.println("food inserted successfully");
                    out.flush();
                    break;
                }
                case "delete food": {
                    Integer key = Integer.parseInt(request.getData());
                    foodDao.deleteFoodFromMenuById(key);
                    out.println("food deleted successfully");
                    out.flush();
                }
                case "bring drink menu": {
                    Map<Integer, Drinks> menu = drinkDao.getDrinksMenu();
                    String messageToClient = gson.toJson(menu);
                    out.println(messageToClient);
                    out.flush();
                    break;
                }
                case "insert drink": {
                    Drinks drink = gson.fromJson(request.getData(), Drinks.class);
                    drinkDao.addDrinkToMenu(Integer.parseInt(request.getName()), drink);
                    System.out.println(drink.toString());
                    System.out.println(foodDao.getFoodMenu().toString());
                    out.println("drink inserted successfully");
                    out.flush();
                    break;
                }
                case "delete drink": {
                    Integer key = Integer.parseInt(request.getData());
                    drinkDao.deleteDrinkFromMenuById(key);
                    out.println("drink deleted successfully");
                    out.flush();
                    break;
                }
                case "refresh tables": {
                    ArrayList<Table> myTables = new ArrayList<>();
                    myTables = tableDao.refreshActiveTables();
                    out.println(gson1.toJson(myTables));
                    break;
                }
                case "update table info": {
                    Table table = Table.tableFromJson(request.getData());
                    tableDao.updateTable(table.getTableNum(), table);
                    out.println("table update successfully");
                    break;
                }
                case "close table": {
                    Table table2 = Table.tableFromJson(request.getData());
                    ArrayList<Table> myTables = tableDao.refreshActiveTables();
                    myTables.set(table2.getTableNum() - 1, new Table(0, table2.getTableNum(), false));
                    tableDao.updateFullActiveTables(myTables);
                    historyDao.addTableToHistory(table2);
                    break;
                }
                case "caculate incomes": {
                    TableHistoryDao historyDao1 = new TableHistoryDao();
                    Integer income = historyDao1.calculateIncome();
                    Integer monthlyIncome = historyDao1.calculateIncomeByMonth(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
                    Integer dailyIncome = historyDao1.calculateIncomeByDay(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
                    Integer tip = historyDao1.calculateTipByDay(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
                    ArrayList<Integer> incomes = new ArrayList<>();
                    incomes.add(income);
                    incomes.add(monthlyIncome);
                    incomes.add(dailyIncome);
                    incomes.add(tip);
                    out.println(gson.toJson(incomes));

                }
                case "calculate income": {
                    Integer income = historyDao.calculateIncome();
                    out.println(income.toString());
                    break;
                }
                case "calculate monthly income": {
                    Integer income = historyDao.calculateIncomeByMonth(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
                    out.println(income.toString());
                    break;
                }
                case "calculate daily income": {
                    Integer income = historyDao.calculateIncomeByDay(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
                    out.println(income.toString());
                    break;
                }
                case "calculate daily tip": {
                    Integer tip = historyDao.calculateTipByDay(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
                    out.println(tip.toString());
                    break;
                }
                case "check product sales": {
                    ISearch kmp = new kmpAlgo();
                    BufferedReader reader;
                    String path = "historyTables.txt";
                    reader = new BufferedReader(new FileReader(path));
                    String txt = reader.readLine();
                    Map<Integer, Food> menuFood = foodDao.getFoodMenu();
                    Map<Integer, Drinks> menuDrink = drinkDao.getDrinksMenu();
                    for (Food food : menuFood.values()) {
                        if (food.getOrderName().equals(request.getData())) {
                            ArrayList<Integer> lstKmp = kmp.indexSearch(txt, request.getData());
                            Integer count = lstKmp.size();
                            out.println(count.toString());
                            reader.close();
                            break;
                        }
                    }
                    for (Drinks drinks : menuDrink.values()) {
                        if (drinks.getOrderName().equals(request.getData())) {
                            ArrayList<Integer> lstKmp = kmp.indexSearch(txt, request.getData());
                            Integer count = lstKmp.size();
                            out.println(count.toString());
                            reader.close();
                            break;
                        }
                    }
                    out.println("no exsist");
                    reader.close();
                    break;
                }
                case "bring daily tabels": {
                    ArrayList<Table> myTables = historyDao.getFullTableHistory();
                    out.println(gson1.toJson(myTables));
                    break;
                }
                default: {
                    out.println("bye");
                    out.flush();
                    break;
                }
            }

//            outputStream.writeObject("bye");
//            outputStream.flush();

            out.close();
            in.close();
            someClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
import java.io.IOException;

public class Main  {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        BufferedReader reader;
//        String path = "historyTables.txt";
//        reader= new BufferedReader(new FileReader(path));
//        String myTablesStr= reader.readLine();
//        System.out.println(myTablesStr);


//        ArrayList<Table> myTables= new ArrayList<>();
////        myTables.add(new Table(3,2,false));
////        myTables.add(new Table(2,1,false));
////        myTables.add(new Table(4,3,false));
//
//        TableHistoryDao historyDao= new TableHistoryDao();
//        historyDao.updateFullHistoryTables(myTables);
//        ArrayList<Table> myTables= new ArrayList<>();
//        ActiveTableDao dao=new ActiveTableDao();
//        for (int i=0; i<9; i++ ){
//            myTables.add(new Table(0,i+1, false));
//        }
//
//        dao.updateFullActiveTables(myTables);

//        dao.updateTable(2, new Table(3,2,true));

//        Gson gson=new Gson();
//        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

//        String json= ((Gson) gson).toJson(this);
//        gson.toJson(myTables);
//        System.out.println(gson.toJson(myTables));


//        myTables= dao.refreshActiveTables();

//        System.out.println(myTables.get(2).getTableId());



//        FoodMenuDao foodDao= new FoodMenuDao();
//        Food f1= new Food(10, "burger");
//        Food f2= new Food(12, "double burger");
//        Food f3= new Food(31, "steak");
//
//        Map<Integer, Food> myMenu =new HashMap<Integer,Food>();
//        myMenu.put(1,f1);
//        myMenu.put(2,f2);
//        myMenu.put(3,f3);
//
//        foodDao.updateFullFoodMenu(myMenu);
//        Food f4= new Food(31, "ezra food");
//
//        foodDao.addFoodToMenu(4,f4);
//
//
//        System.out.println(foodDao.getFoodMenu());


//        String str="{\"1\":{\"price\":11,\"orderName\":\"burger\"},\"2\":{\"price\":22,\"orderName\":\"pita\"},\"3\":{\"price\":33,\"orderName\":\"pizza\"}}";
//        Gson gson= new Gson();
//        Type type = new TypeToken<HashMap<Integer, Food>>(){}.getType();
//        HashMap<Integer,Food> myData = new HashMap<>();
//        myData = gson.fromJson(str, type);
//        System.out.println(myData.toString());
//        DrinksMenuDao drinksMenuDao = new DrinksMenuDao();

//        ArrayList<Order> orderList= new ArrayList<>();
//        Food f1= new Food( 100, "hamburger");
//        Drinks d1=new Drinks( 30, "vodka");
//
//        orderList.add(f1);
//        orderList.add(d1);
//        for (Order order: orderList) {
//            System.out.println(order.getOrderName() +" "+ order.getPrice());
//        }

//                ArrayList<Food> foodArrayList=new ArrayList<>();
//        Integer totalAmount=0;
//        Food f1= new Food( 100, "hamburger");
//        Food f2= new Food( 80, "pasta");
//
//        foodArrayList.add(f1);
//        foodArrayList.add(f2);
//
//        for (Food food: foodArrayList) {
//            totalAmount+= food.getPrice();
//        }
//
//        System.out.println(totalAmount);
//
//        Table table=new Table(4);
//        table.inviteFood(f1);
//        table.inviteFood(f2);

//        for (Food food: table.getFood_order()) {
//            System.out.println(food.getOrderName() + " "+ food.getPrice());
//        }

//        System.out.println(table.getTotalAmount());
//
//        Payment payment= table.getPayment();
//        System.out.println(payment.getTotal_amount());
//        Payment payment= table.getPayment();
//        payment.payBill(100);
//        payment.payBill(20);
//
//        table.printBill();




//        List<Food> list = new ArrayList<>();
//        MenuFileDao dao = new MenuFileDao();
//
//        Food o1 = new Food(11, "burger");
//        Food o2 = new Food(22, "pita");
//        Food o3 = new Food(33, "pizza");


//
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("menu.txt"));
//        out.writeObject(menu);
//        out.close();



//        dao.addFood(1,o1);
//        dao.addFood(2,o2);
//        dao.addFood(3,o3);


// ----------------------------------------------------------------------------------------------


//        check for add function
//        Food o4 = new Food(20,"Baget");
//        dao.add(4,o4);
//        System.out.println(dao.get(4));


//----------------------------------------------------------------------------------------------


//        check for get function
//        System.out.println(dao.get(1));;


//----------------------------------------------------------------------------------------------


//        check for getALL function
//        list = dao.getAll();
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.get(2));



//----------------------------------------------------------------------------------------------


//        check search function
//        System.out.println(dao.search("pizza"));


//----------------------------------------------------------------------------------------------


//        check for delete function
//        dao.delete(2);
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("menu.txt"));
//        Map<Integer, Food> menu = (Map<Integer, Food>) objectInputStream.readObject();
//        System.out.println(menu);
//        System.out.println(menu.get(1));
//        System.out.println(menu.get(2));
//        System.out.println(menu.get(3));



//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.get(2));
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream("menu.txt"));
//        Map<Integer, Food> dan = new HashMap<>();
//        dan = (Map<Integer,Food>) in.readObject();
//        dan.remove(1);
//        System.out.println(dan.get(1));
//        System.out.println(dan.get(2));
//        in.close();
//

//     ---------------------------------------------------------------------------------------

//        BackupAndRestore backupAndRestore = new BackupAndRestore();
//        String foodMenuFile = "FoodMenu.txt";
//        String foodBackupFile = "food_backup.txt";
//        Food food = new Food(10, "Pizza");
//        try (FileOutputStream fos = new FileOutputStream(foodMenuFile);
//             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeObject(food);
//        }
//        backupAndRestore.backup(foodMenuFile, foodBackupFile, 0, 1000);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Food restoredFood = backupAndRestore.restore(foodBackupFile);
//        FoodMenuDao dao = new FoodMenuDao();
//        System.out.println(dao.getFoodMenu());


    }
}
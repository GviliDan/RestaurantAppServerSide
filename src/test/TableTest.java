package test;

import TableModel.Food;
import TableModel.Payment;
import TableModel.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TableTest {

    @Test
    public void constructorTest(){
        Table table=new Table(10,1,true);
        Assertions.assertEquals(10,table.getNum_of_diners());
        Assertions.assertEquals(0,table.getTableId());
//        Assertions.assertEquals(1,IdForTables);

        Table table1=new Table();
        Assertions.assertEquals(2,table1.getNum_of_diners());
        Assertions.assertEquals(1,table1.getTableId());
//        Assertions.assertEquals(2,IdForTables);

        Table table2=new Table();
        Assertions.assertEquals(2,table2.getTableId());
    }

    @Test
    public void inviteTest(){
        Table table=new Table(4,1,true);
        Food f1= new Food( 100,"hamburger");
        table.inviteFood(f1);
        Assertions.assertEquals(f1,table.getFood_order().get(0));
        Assertions.assertEquals(100,table.getTotalAmount());
        Payment payment= table.getPayment();
        Assertions.assertEquals(100,payment.getTotal_amount());
    }

    @Test
    public void tableTojsonTest(){
        Table table=new Table(3,1,true);
        String json= table.tableTojson();
        System.out.println(json);
    }
}

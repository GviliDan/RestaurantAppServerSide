package test;

import TableModel.Payment;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;



public class PayTest {

    @Test
    public void constructorPaymentTest(){
        Payment payment=new Payment();
        payment.setTotal_amount(100);
        Assertions.assertEquals(100,payment.getLeft_payment());
        Assertions.assertEquals(0,payment.getTotal_payment());
    }

    @Test
    public void left_payment_test(){
        Payment payment=new Payment(150);
        payment.payBill(50);
        payment.payBill(20);
        Assertions.assertEquals(80,payment.getLeft_payment());
    }

    @Test
    public void AddTipByPrecentTest(){
        Payment payment= new Payment(100);
        payment.add_Tip_by_precent(10);
        Assertions.assertEquals(110,payment.getTotal_amount());
        Assertions.assertEquals(110 , payment.getLeft_payment());
    }

    @Test
    public void AddTipByShekelsTest(){
        Payment payment= new Payment(100);
        payment.add_Tip_by_shekels(12);
        Assertions.assertEquals(112,payment.getTotal_amount());
        Assertions.assertEquals(112 , payment.getLeft_payment());
    }
}

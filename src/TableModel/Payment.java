package TableModel;

import java.io.Serializable;

public class Payment implements Serializable {
    private Integer total_amount;
    private Integer total_payment;
    private Integer left_payment;

    private Integer tip;


    public Payment(){
        this.total_amount=0;
        this.total_payment=0;
        this.left_payment=total_amount;
        this.tip=0;

    }
    public Payment(Integer total_amount) {
        this.total_amount = total_amount;
        this.total_payment = 0;
        this.left_payment = total_amount;
        this.tip=0;
    }

    public Integer getTip() {
        return tip;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public Integer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Integer total_amount) {
        this.total_amount = total_amount;
        this.left_payment=total_amount-this.getTotal_payment();
    }

    public Integer getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(Integer total_payment) {
        this.total_payment = total_payment;
    }

    public Integer getLeft_payment() {
        return left_payment;
    }

    public void setLeft_payment(Integer left_payment) {
        this.left_payment = left_payment;
    }

    public void payBill (Integer amount){
        Integer my_total_payment= this.getTotal_payment();
        Integer my_left_payment= this.getLeft_payment();
        this.setTotal_payment(my_total_payment+amount);
        this.setLeft_payment(my_left_payment-amount);
        if(this.left_payment<0){
            this.tip= 0-this.left_payment;
            this.left_payment=0;
        }
    }

    public Integer SplitPayment(Integer num_of_persons){
        Integer my_total_amount= this.getTotal_amount();
        return my_total_amount/num_of_persons;
    }


    public void add_Tip_by_precent(Integer precent){
        Integer my_total_amount = this.getTotal_amount();
        Integer tip= (precent*100)/my_total_amount;
        this.setTotal_amount(my_total_amount+tip);
    }

    public void add_Tip_by_shekels(Integer shekels){
        Integer my_total_amount = this.getTotal_amount();
        my_total_amount=my_total_amount+shekels;
        this.setTotal_amount(my_total_amount);
    }
}

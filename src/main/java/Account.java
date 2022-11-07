import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Account {
    static int AccCounter = 1;
    String _id;
    String id;
    String billing_address;
    boolean is_closed;
    Date open;
    Date closed;
    int balance;
    ArrayList<Order> Orders;
    
    public Account(Customer c, String id, String billing_address, int balance) {
        this._id = String.format("account%d",AccCounter);
        AccCounter++;
        this.id = id;
        this.billing_address = billing_address;
        this.balance = balance;
        open = new Date();
        is_closed = false;
    }
    public void printConnected(){
        System.out.println("this Account is connected to these orders:");
        for (Order order:Orders) {
            System.out.println(order._id);
        }
    }
}
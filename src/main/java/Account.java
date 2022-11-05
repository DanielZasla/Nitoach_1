import java.util.Date;

public class Account {
    String id;
    String billing_address;
    boolean is_closed;
    Date open;
    Date closed;
    int balance;
    
    public Account(Customer c, String id, String billing_address, int balance) {
        this.id = id;
        this.billing_address = billing_address;
        this.balance = balance;
        open = new Date();
        is_closed = false;
    }
}
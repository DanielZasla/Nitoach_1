import java.util.Date;

public class Account {
    String id;
    String billing_address;
    boolean is_closed;
    Date open;
    Date closed;
    int balance;

    public Account(String id, String billing_address, boolean is_closed, int balance) {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = is_closed;
        this.balance = balance;
        open = new Date();
    }
}
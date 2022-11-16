import java.util.ArrayList;
import java.util.Date;

public class Account {
    static int AccCounter = 1;
    String _id;
    String id;
    String billing_address;
    boolean is_closed;
    Date open;
    Date closed;
    int balance;

    ArrayList<Order> Orders = new ArrayList<>();
    ArrayList<Payment> Payments = new ArrayList<>();
    Customer customer;
    ShoppingCart shoppingCart;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Order getLastOrder(){
        if (Orders.size() == 0)
            return null;
        return getOrders().get(Orders.size() - 1);
    }

    public Account(String id, String billing_address, int balance) {
        this._id = String.format("account%d",AccCounter);
        AccCounter++;
        this.id = id;
        this.billing_address = billing_address;
        this.balance = balance;
        open = new Date();
        is_closed = false;
    }
    public String toString() {
        return "Id: "+ id+"\nBilling address: " + billing_address + "\nIs closed: " + is_closed
                + "\nOpen: "+open+"\nClosed: "+ closed +"\nBalance: "+balance;
    }


    public void printConnected(){
        if (Orders.size() != 0)
            System.out.println("This Account is connected to these orders:");
        for (Order order:Orders) {
            System.out.println(order._id);
        }
        System.out.println("This Account is also connected to this Customer: "+ customer._id);
        System.out.println("This Account is also connected to this Shopping cart: "+shoppingCart._id);
    }

    public ArrayList<Order> getOrders() {
        return Orders;
    }

    public ArrayList<Payment> getPayments() {
        return Payments;
    }
}
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class ShoppingCart {
    ArrayList<LineItem> LItems = new ArrayList<>();
    static int ShopCCounter = 1;
    String _id;
    Date created;
    User user;
    Account acc;

    public void setUser(User user) {
        this.user = user;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public ShoppingCart() {
        this._id = String.format("shoppingCart%d",ShopCCounter);
        ShopCCounter++;
        this.created = new Date();
    }

    public ArrayList<LineItem> getLItems() {
        return LItems;
    }

    public void printDetails() {
        System.out.println("Created: " + created);
    }
    public void printConnected(){
        System.out.println("this Shopping Cart is connected to these Line Items:");
        for (LineItem item:LItems) {
            System.out.println(item._id);
        }
        System.out.println("this Shopping Cart is also connected to: "+ acc._id);
        System.out.println("this Shopping Cart is also connected to: "+ user._id);
    }
}

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

    public ShoppingCart(User user, Account acc) {
        this._id = String.format("shoppingCart%d",ShopCCounter);
        ShopCCounter++;
        this.user = user;
        this.acc = acc;
        this.created = new Date();
    }

    public ArrayList<LineItem> getLItems() {
        return LItems;
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

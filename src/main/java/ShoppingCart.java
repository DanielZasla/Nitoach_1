import java.util.ArrayList;
import java.util.Date;

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

    public void addItem(LineItem item){
        LItems.add(item);
    }

    public ArrayList<LineItem> getLItems() {
        return LItems;
    }

    public String toString() {
        return "Created: " + created.toString();
    }
    public void printConnected(){
        System.out.println("This Shopping Cart is connected to these Line Items:");
        for (LineItem item:LItems) {
            System.out.println(item._id);
        }
        System.out.println("This Shopping Cart is also connected to: "+ acc._id);
        System.out.println("This Shopping Cart is also connected to: "+ user._id);
    }
}

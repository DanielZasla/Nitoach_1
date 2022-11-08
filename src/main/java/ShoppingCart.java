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
}

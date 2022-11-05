import java.util.Date;
import java.util.Vector;

public class ShoppingCart {
    Date created;
    User user;
    Account acc;
    Vector<LineItem> itmlist;

    public ShoppingCart(User user, Account acc) {
        this.user = user;
        this.acc = acc;
        created = new Date();
    }
}

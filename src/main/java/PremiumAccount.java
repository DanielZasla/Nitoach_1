import java.util.ArrayList;

public class PremiumAccount extends Account{
    ArrayList<Product> prods;

    public PremiumAccount(String id, String billing_address, boolean is_closed, int balance) {
        super(id, billing_address, is_closed, balance);
    }
}

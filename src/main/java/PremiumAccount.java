import java.util.ArrayList;

public class PremiumAccount extends Account{
    ArrayList<Product> prods;

    public PremiumAccount(Customer c, String id, String billing_address, int balance) {
        super(c, id, billing_address, balance);
        this.prods = new ArrayList<>();
    }
}

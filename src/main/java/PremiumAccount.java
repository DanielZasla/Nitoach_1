public class PremiumAccount extends Account{
    Product prod;

    public PremiumAccount(String id, String billing_address, boolean is_closed, int balance) {
        super(id, billing_address, is_closed, balance);
    }
}

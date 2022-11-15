import java.util.ArrayList;

public class PremiumAccount extends Account{
    ArrayList<Product> prods;

    public PremiumAccount(String id, String billing_address, int balance) {
        super(id, billing_address, balance);
        this.prods = new ArrayList<>();
    }

    public ArrayList<Product> getProds() {
        return prods;
    }

    public void printConnected(){
        super.printConnected();
        if (!prods.isEmpty()){
            System.out.println("This Premium Account is connected to these Products:");
            for (Product p :prods){
                System.out.println(p._id);
            }
        }
    }
}

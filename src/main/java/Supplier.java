import java.util.ArrayList;

public class Supplier {
    ArrayList<Product> Products = new ArrayList<>();
    static int SupCount = 1;
    String _id;
    String id;
    String name;

    public Supplier(String id, String name){
        this._id = String.format("supplier%d",SupCount);
        SupCount++;
        this.id = id;
        this.name = name;
    }
}

import java.util.ArrayList;

public class Product {
    ArrayList<LineItem> LItems = new ArrayList<>();
    static int ProdCounter = 1;
    String _id;
    String name;
    Supplier supplier;
    int price;
    int quantity;

    PremiumAccount premacc;


    public Product(String name, Supplier supplier){
        this._id = String.format("product%d",ProdCounter);
        ProdCounter++;
        this.name = name;
        this.supplier = supplier;
        this.premacc = null;
        this.price = 0;
        this.quantity = 0;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean Link(PremiumAccount prem){
        if (premacc != null){
            return false;
        }
        this.premacc = prem;
        return true;
    }

    public void addLineItem(LineItem l){
        LItems.add(l);
    }

    public void setPremacc(PremiumAccount premacc) {
        this.premacc = premacc;
    }

    public void printDetails() {
        System.out.println("ID: " + _id+"\nName: " + name);
    }
    public void printConnected(){
        System.out.println("This product is connected to these Line Items:");
        for (LineItem item:LItems) {
            System.out.println(item._id);
        }
        if(premacc!=null)
            System.out.println("This product is also connected to this Premium Account: "+premacc._id);
        System.out.println("This product is also connected to this supplier: "+supplier._id);
    }

}


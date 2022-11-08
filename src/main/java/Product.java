import java.util.ArrayList;

public class Product {
    ArrayList<LineItem> LItems = new ArrayList<>();
    static int ProdCounter = 1;
    String _id;
    String name;
    Supplier supplier;

    PremiumAccount premacc;


    public Product(String name, Supplier supplier){
        this._id = String.format("product%d",ProdCounter);
        ProdCounter++;
        this.name = name;
        this.supplier = supplier;
        this.premacc = null;
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
    public void printConnected(){
        System.out.println("this product is connected to these Line Items:");
        for (LineItem item:LItems) {
            System.out.println(item._id);
        }
        System.out.println("this product is also connected to this Premium Account: "+premacc);
    }

}


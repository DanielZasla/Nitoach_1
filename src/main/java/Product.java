public class Product {
    //LineItem lnitem = null;
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



}


public class Product {
    //LineItem lnitem = null;
    String id;
    String name;
    Supplier supplier;

    PremiumAccount premacc;


    public Product(String id, String name, Supplier supplier){
        this.id = id;
        this.name = name;
        this.supplier = supplier;
    }

}


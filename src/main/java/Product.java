public class Product {
    //LineItem lnitem = null;
    String id;
    String name;
    Supplier supplier;
    PremiumAccount premacc;
    //TODO
    // Daniel my brother in Christ, I dont think that Product's constructor needs to have lineitem and premaccount
    // in fact when its created at its most basic it just needs its fields and a Supplier that supplies it
    // ive created a new constructor and commented yours out, let me know if im the dum and you are the smert

/*    public Product(LineItem lnitem, String id,String name, PremiumAccount premacc) {

    }*/
    public Product(String id, String name, Supplier supplier){
        this.id = id;
        this.name = name;
        this.supplier = supplier;
    }

}


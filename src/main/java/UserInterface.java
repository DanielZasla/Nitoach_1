import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    static ArrayList<Account> AccountList = new ArrayList<>();
    static ArrayList<Customer> CustomerList = new ArrayList<>();
    static ArrayList<LineItem> LineItemList = new ArrayList<>();
    static ArrayList<Order> OrderList = new ArrayList<>();
    static ArrayList<Payment> PaymentList = new ArrayList<>();
    static ArrayList<Product> ProductList = new ArrayList<>();
    static ArrayList<ShoppingCart> ShoppingCartList = new ArrayList<>();
    static ArrayList<Supplier> SupplierList = new ArrayList<>();
    static ArrayList<User> UserList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
    private User connected_user;


    public static void main(String[] args){
        UserInterface UI = new UserInterface();
        UI.run();
    }


    public void run() {
        String loginId,password,address, orderId,productName,supplierName;
        PopulateSystem();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Welcome " + ((connected_user!= null)? connected_user.login_id: "Guest"));
            System.out.println("1) Add User\n2) Remove User\n3) Login User\n4) Logout User\n5) Create New Order\n6) Add Product To Order\n7) Display Order\n8) Link Product\n9) Add Product\n10) Delete Product\n11) Show All Object\n12) Show Object By Id\n0) Exit\n");
            int pick = scanner.nextInt();
            switch (pick){
                default:
                    System.out.println("Illegal character.");
                    break;
                case 1:
                    System.out.println("Please enter login id for the new user: ");
                    loginId = scanner.next();
                    AddUser(loginId);
                    break;
                case 2:
                    System.out.println("Please enter login id for the user you want to remove: ");
                    loginId = scanner.next();
                    RemoveUser(loginId);
                    break;
                case 3:
                    if (isConnected()){
                        System.out.println("A different user is connected now.");
                        break;
                    }
                    System.out.println("Please enter login id for the user: ");
                    loginId = scanner.next();
                    System.out.println("Please enter the password for this user:");
                    password = scanner.next();
                    LoginUser(loginId,password);
                    break;
                case 4:
                    LogoutUser();
                    break;
                case 5:
                    if (isConnected()){
                        System.out.println("Please enter the order Address: ");
                        address = scanner.next();
                        CreateOrder(address);
                    } else {
                        System.out.println("No user is connected at the moment.");
                    }

                    break;
                case 6:
                    if (isConnected()){
                        System.out.println("Please enter the order id: ");
                        orderId = scanner.next();
                        System.out.println("Please enter login id for the user: ");
                        loginId = scanner.next();
                        System.out.println("Please enter the Product name:");
                        productName = scanner.next();
                        AddProductToOrder(orderId,loginId,productName);
                    } else {
                        System.out.println("No user is connected at the moment.");
                    }

                    break;
                case 7:
                    DisplayOrder();
                    break;
                case 8:
                    if(isConnected()){
                        System.out.println("Please enter the Product name:");
                        productName = scanner.next();
                        System.out.println("Please enter the price: ");
                        int price = scanner.nextInt();
                        System.out.println("Please enter the quantity: ");
                        int quantity = scanner.nextInt();
                        LinkProduct(productName,price,quantity);
                    } else {
                        System.out.println("No user is connected at the moment.");
                    }
                    break;
                case 9:
                    System.out.println("Please enter the Product name:");
                    productName = scanner.next();
                    System.out.println("Please enter the supplier name:");
                    supplierName = scanner.next();
                    AddProduct(productName,supplierName);
                    break;
                case 10:
                    System.out.println("Please enter the Product name:");
                    productName = scanner.next();
                    DeleteProduct(productName);
                    break;
                case 11:
                    ShowAllObjects();
                    break;
                case 12:
                    System.out.println("Please enter Id:");
                    String id = scanner.next();
                    ShowObjectId(id);
                    break;
                case 0:
                    return;
            }
            System.out.println("\n\n");
        }
    }

    private void AddUser(String login_id){
        if (getUser(login_id)!= null){
            System.out.printf("Uh oh, ID %s is already in the system.%n",login_id);
            return;
        }

        User u = createUser(login_id);
        Customer c = createCustomer(login_id);
        Account a = createAccount(login_id);
        ShoppingCart sc = new ShoppingCart();

        linkUserClasses(u,c,a,sc);

        UserList.add(u);
        CustomerList.add(c);
        AccountList.add(a);
        ShoppingCartList.add(sc);

        System.out.println("New User successfully created, returning to main menu.");
    }

    // TODO: add this to 3 and 8

    private void RemoveUser(String login_id){
        if (connected_user != null){
            System.out.println("You must first log off the currently connected user in order to remove a user."); return;}

        User user = getUser(login_id);
        Account acc = getAccount(login_id);

        if (user == null){
            System.out.println("No such user exists"); return;
        }


        // Removing all payments related to the account
        if (acc != null)
            for (Payment p : acc.getPayments())
                PaymentList.remove(p);

        // Here we delete all the line items that are related to this user's shopping cart
        for (LineItem l : user.getCart().getLItems())
            LineItemList.remove(l);

        // Removing all orders
        if (acc != null)
            for (Order o : acc.getOrders())
                OrderList.remove(o);

        // If it's a premium account, remove it from the products.
        if (acc instanceof PremiumAccount)
            for (Product p : ((PremiumAccount) acc).getProds())
                p.setPremacc(null);

        // Removing from the lists.
        ShoppingCartList.remove(user.getCart());
        AccountList.remove(acc);
        assert acc != null;
        CustomerList.remove(acc.customer);
        UserList.remove(user);

        System.out.println("User " + login_id + " has been removed.");
    }

    private void LoginUser(String login_id, String password){

        User user;
        if ((user = getUser(login_id)) != null){
            if (user.password.equals(password)) {
                connected_user = user;
                System.out.println("User connected");
                return;
            }
        }
        System.out.println("Login details are incorrect.");
    }

    private void LogoutUser(){
        if (connected_user == null)
            System.out.println("No user is logged in.");
        else
        {
            connected_user = null;
            System.out.println("User logged out successfully.");
        }
    }

    private void CreateOrder(String address){
        Address add = new Address(address);
        Account acc = connected_user.getCustomer().getAccount();
        Order newOrder = new Order(add, acc);
        acc.getOrders().add(newOrder);
        OrderList.add(newOrder);
    }

    private void AddProductToOrder(String order_id, String login_id, String product_name){
        Account prem = getAccount(login_id);
        Account me = connected_user.getCustomer().getAccount();
        Order o = null;
        if(prem==null){
            System.out.println("The account you are looking does not exits."); return;
        }

        for(Order ord: me.Orders){
            if(ord.number.equals(order_id)){
                o = ord;
            }
        }
        if(o==null){
            System.out.println("The account " + me.id + " does not have an order with that number."); return;
        }

        Product p = getProduct(product_name);
        if(p==null || p.premacc == null){
            System.out.println("Product doesn't exist or isn't linked to a premium account."); return;
        }

        if (p.quantity <= 0) {
            System.out.println("Product ran out."); return;
        }

        LineItem item = new LineItem(p);
        if (item.price > me.balance){
            System.out.println("Insufficient balance in the account to order the product."); return;
        }

        Payment payment = o.addItem(item);
        PaymentList.add(payment);
        p.premacc.balance += p.price;
        me.shoppingCart.addItem(item);
        item.shoppingCart = me.shoppingCart;
        LineItemList.add(item);
        item.order = o;
        System.out.println("Product: "+product_name+" has been added to order: "+order_id);

    }

    private void DisplayOrder(){
        if (connected_user == null){
            System.out.println("No user is connected. Please log in to display order."); return; }


        Order o = connected_user.getCustomer().getAccount().getLastOrder();
        if (o == null)
            System.out.println("Connected user has no orders in its account.");
        else {
            System.out.println("Last placed order:");
            System.out.println(o);
        }
    }

    private void LinkProduct(String product_name, int price, int quantity){
        Account acc = getAccount(connected_user.getLogin_id());
        if (!(acc instanceof PremiumAccount)){
            System.out.println("You are not a premium account and cannot link products.");
            return;
        }
        Product prod = getProduct(product_name);
        if (prod == null){
            System.out.println("No product exits with that name.");
            return;
        }
        if (!prod.Link((PremiumAccount) acc)){
            System.out.println("This product is already linked to another Premium user.");
            return;
        }
        ((PremiumAccount) acc).prods.add(prod);
        prod.price = price;
        prod.quantity = quantity;
    }

    private void AddProduct(String product_name, String supplier_name){
        Supplier s = getSupplier(supplier_name);
        if(s != null){
            Product p = new Product(product_name,s);
            ProductList.add(p);
            s.Products.add(p);
            System.out.println("Product: "+product_name+" has been added to supplier: "+supplier_name);
        }
        else{
            System.out.println("No suppliers with that name exist.");
        }
    }

    private void ShowAllObjects() {
        boolean flag = false;
        System.out.println("Accounts:");
        System.out.print("\t");
        for (Account account : AccountList) {
            System.out.print((flag?", ":"")+account._id);
            flag = true;
        }
        flag = false;
        System.out.println();
        System.out.println("Customers:");
        System.out.print("\t");
        for (Customer customer : CustomerList) {
            System.out.print((flag?", ":"")+customer._id);
            flag = true;
        }
        flag = false;
        System.out.println();
        System.out.println("Line Items:");
        System.out.print("\t");
        for (LineItem lineItem : LineItemList) {
            System.out.print((flag?", ":"")+lineItem._id);
            flag = true;
        }
        flag = false;
        System.out.println();
        System.out.println("Orders:");
        System.out.print("\t");
        for (Order order : OrderList) {
            System.out.print((flag?", ":"")+order._id);
            flag = true;
        }
        flag = false;
        System.out.println();
        System.out.println("Payments:");
        System.out.print("\t");
        for (Payment payment : PaymentList) {
            System.out.print((flag?", ":"")+payment._id);
            flag = true;
        }
        flag = false;
        System.out.println();
        System.out.println("Products:");
        System.out.print("\t");
        for (Product product : ProductList) {
            System.out.print((flag?", ":"")+product._id);
            flag = true;
        }
        flag = false;
        System.out.println();
        System.out.println("Shopping Carts:");
        System.out.print("\t");
        for (ShoppingCart shoppingCart : ShoppingCartList) {
            System.out.print((flag?", ":"")+shoppingCart._id);
            flag = true;
        }
        flag = false;
        System.out.println();
        System.out.println("Suppliers:");
        System.out.print("\t");
        for (Supplier supplier : SupplierList) {
            System.out.print((flag?", ":"")+supplier._id);
            flag = true;
        }
        flag = false;
        System.out.println();
        System.out.println("Users:");
        System.out.print("\t");
        for (User user : UserList) {
            System.out.print((flag?", ":"")+user._id);
            flag = true;
        }
        System.out.println();
    }
    private void DeleteProduct(String product_Name){
        Product product = getProduct(product_Name);
        ArrayList<LineItem> helper = new ArrayList<>();
        if (product != null){
            for (LineItem item : LineItemList) {
                if(item.product.name.equals(product_Name)) {
                    item.order.LItems.remove(item);
                    item.shoppingCart.LItems.remove(item);
                    helper.add(item);
                }
            }
            for(LineItem item : helper){
                LineItemList.remove(item);
            }
            if(product.premacc!=null) {
                product.premacc.prods.remove(product);
            }
            product.supplier.Products.remove(product);
            ProductList.remove(product);
            System.out.println("Product has been deleted");
        }
        else {
            System.out.println("No such product exists.");
        }

    }

    private void ShowObjectId(String id){
        System.out.println("Please choose the object type to view:");
        System.out.println("1) Account\n2) Customer\n3) Line Item\n4) Order\n5) Payment\n6) Product\n7) Shopping Cart\n8) Supplier\n9) User");
        int pick = scanner.nextInt();
        switch (pick){
            case 1:
                for (Account account : AccountList) {
                    if(Objects.equals("account" + id, account._id)) {
                            System.out.println(account);
                        account.printConnected();
                        return;
                    }
                }
                break;
            case 2:
                for (Customer customer : CustomerList) {
                    if (Objects.equals("customer" + id, customer._id)) {
                        System.out.println(customer);
                        customer.printConnected();
                        return;
                    }
                }
                break;
            case 3:
                for (LineItem lineItem : LineItemList) {
                    if (Objects.equals("lineItem" + id, lineItem._id)) {
                        System.out.println(lineItem);
                        lineItem.printConnected();
                        return;
                    }
                }
                break;
            case 4:
                for (Order order : OrderList) {
                    if (Objects.equals("order" + id, order._id)) {
                        System.out.println(order);
                        order.printConnected();
                        return;
                    }
                }
                break;
            case 5:
                for (Payment payment : PaymentList) {
                    if (Objects.equals("payment" + id, payment._id)) {
                        System.out.println(payment);
                        payment.printConnected();
                        return;
                    }
                }
                break;
            case 6:
                for (Product product : ProductList) {
                    if (Objects.equals("product" + id, product._id)) {
                        System.out.println(product);
                        product.printConnected();
                        return;
                    }
                }
                break;
            case 7:
                for (ShoppingCart shoppingCart : ShoppingCartList) {
                    if (Objects.equals("shoppingCart" + id, shoppingCart._id)) {
                        System.out.println(shoppingCart);
                        shoppingCart.printConnected();
                        return;
                    }
                }
                break;
            case 8:
                for (Supplier supplier : SupplierList) {
                    if (Objects.equals("supplier" + id, supplier._id)) {
                        System.out.println(supplier);
                        supplier.printConnected();
                        return;
                    }
                }
                break;
            case 9:
                for (User user : UserList) {
                    if (Objects.equals("user" + id, user._id)) {
                        System.out.println(user);
                        user.printConnected();
                        return;
                    }
                }
                break;
            default:
                System.out.println("Illegal character.");
                return;
        }
        System.out.println("ID not found.");
    }

    // Private Methods for ArrayList Functionality

    /**
     * @param id ID of the Account
     * @return The Account with the matching ID if exists, else null
     */
    private static Account getAccount(String id){
        for (Account a : AccountList){
            if (a.id.equals(id)){
                return a;
            }
        }
        return null;
    }


    /**
     * @param name Name of the Product
     * @return The Product with the matching name if exists, else null
     */
    private static Product getProduct(String name){
        for (Product p : ProductList){
            if (p.name.equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * @param id ID of the ShoppingCart
     * @return The ShoppingCart with the matching ID if exists, else null
     */
    private static ShoppingCart getShoppingCart(String id){
        for (ShoppingCart s : ShoppingCartList){
            if (s._id.equals(id)){
                return s;
            }
        }
        return null;
    }

    /**
     * @param id ID of the Supplier
     * @return The Supplier with the matching ID if exists, else null
     */
    private static Supplier getSupplier(String id){
        for (Supplier s : SupplierList){
            if (s.id.equals(id)){
                return s;
            }
        }
        return null;
    }

    /**
     * @param id ID of the User
     * @return The User with the matching ID if exists, else null
     */
    private static User getUser(String id){
        for (User u : UserList){
            if (u.login_id.equals(id)){
                return u;
            }
        }
        return null;
    }

    /**
     * This method is called at the beginning of system runtime to populate the lists with initial entities
     */
    private void PopulateSystem(){
        SupplierList.add(new Supplier("Osem", "Osem"));
        SupplierList.add(new Supplier("EastWest", "EastWest"));
        Product bamba = new Product("Bamba", getSupplier("Osem"));
        bamba.supplier.Products.add(bamba);
        ProductList.add(bamba);
        ProductList.add(new Product("Ramen", getSupplier("EastWest")));
        User daniU = new User("Dani", "Dani123");
        Customer daniC = new Customer("Dani", new Address("Dani St."), "052239478","Danimail");
        Account daniA = new Account("Dani", "Dani's House", 100);
        ShoppingCart daniSC = new ShoppingCart();
        linkUserClasses(daniU,daniC,daniA,daniSC);
        User danaU = new User("Dana", "Dana123");
        Customer danaC = new Customer("Dana", new Address("Dana St."), "052239477","Danamail");
        PremiumAccount danaA = new PremiumAccount("Dana", "Dana's House", 100);
        ShoppingCart danaSC = new ShoppingCart();
        linkUserClasses(danaU,danaC,danaA,danaSC);
        danaA.prods.add(bamba);
        bamba.price = 1;
        bamba.quantity = 1;
        bamba.premacc = danaA;
        UserList.add(daniU);
        UserList.add(danaU);
        CustomerList.add(daniC);
        CustomerList.add(danaC);
        AccountList.add(daniA);
        AccountList.add(danaA);
        ShoppingCartList.add(daniSC);
        ShoppingCartList.add(danaSC);

    }

    /**
     * Creates a User with the user's given information.
     * @param login_id Given ID.
     * @return User
     */
    private User createUser(String login_id){
        System.out.println("Creating user (ID: " + login_id + "):");
        System.out.println("Enter password for the user: ");
        String password = scanner.nextLine();

        return new User(login_id, password);
    }

    /**
     * Creates a Customer with the user's given information.
     * @param login_id Given ID.
     * @return Customer
     */
    private Customer createCustomer(String login_id){
        System.out.println("Creating customer (ID: " + login_id + "):");
        System.out.println("Enter address: ");
        Address address = new Address(scanner.nextLine());
        System.out.println("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        return new Customer(login_id, address, phone, email);
    }

    /**
     * Creates an Account with the user's given information.
     * @param login_id Given ID.
     * @return Account
     */
    private Account createAccount(String login_id){
        System.out.println("Creating account (ID: " + login_id + "):");
        System.out.println("Enter billing address: ");
        String billingaddress = scanner.nextLine();
        System.out.println("Enter initial balance: ");
        int balance = scanner.nextInt();
        String premium;
        while(true){
            scanner.nextLine();
            System.out.println("Would you like to sign up to our Premium Account program? (y/n)");
            premium = scanner.nextLine();
            if (premium.equalsIgnoreCase("y"))
                return new PremiumAccount(login_id, billingaddress, balance);
            else if (premium.equalsIgnoreCase("n")) {
                return new Account(login_id, billingaddress, balance);
            }
            else
                System.out.println("Invalid input.");
        }
    }

    private void linkUserClasses(User u, Customer c, Account a, ShoppingCart s){
        u.setCustomer(c);
        u.setCart(s);
        c.setUser(u);
        c.setAccount(a);
        a.setShoppingCart(s);
        a.setCustomer(c);
        s.setAcc(a);
        s.setUser(u);
    }

    private boolean isConnected(){
        return connected_user!=null;
    }


}
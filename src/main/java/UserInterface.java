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


    public static void main(String[] args) {
        PopulateSystem();
    }

    private void AddUser(String login_id){
        if (getUser(login_id)!= null){
            System.out.printf("Uh oh, ID %s is already in the system.%n",login_id);
            return;
        }

        User u = createUser(login_id);
        Customer c = createCustomer(login_id);
        u.setCustomer(c);
        Account a = createAccount(c, login_id);
        ShoppingCart sc = createShoppingCart(u, a);

        UserList.add(u);
        CustomerList.add(c);
        AccountList.add(a);
        ShoppingCartList.add(sc);

        System.out.println("New User successfully created, returning to main menu.");
    }


    private void RemoveUser(String login_id){
        if (connected_user != null){
            System.out.println("You must first log off the currently connected user in order to remove a user.");
        } else {
            User user = getUser(login_id);
            if (user == null){
                System.out.println("No such user exists");
                return;
            }
            String id = user.getLogin_id();
            ShoppingCartList.remove(user.getCart());
            AccountList.remove(getAccount(id));
            CustomerList.remove(user.getCustomer());
            UserList.remove(user);
            System.out.println("User has been removed.");
        }
    }

    private void LoginUser(String login_id, String password){
        if (connected_user!=null){
            System.out.println("A different user is connected right now.");
            return;
        }
        User user;
        if ((user = getUser(login_id)) != null){
            if (user.password.equals(password)) {
                connected_user = user;
                return;
            }
        }
        System.out.println("Login details are incorrect.");
    }

    private void CreateOrder(String address){
        // TODO: Create Order
    }

    private void AddProductToOrder(String order_id, String login_id, String product_name){
        Account a = getAccount(login_id);
        //Order o = getOrder(order_id);
        Order o = null;
        if(a==null){
            System.out.println("The account you are looking does not exits."); return;
        }
        for(Order ord: a.Orders){
            if(ord.number.equals(order_id)){
                o = ord;
            }
        }
        if(o==null){
            System.out.println("The account " + a.id + " does not have an order with that number."); return;
        }
        Product p = getProduct(product_name);
        if(p==null){
            System.out.println("The order " + o.number + " does not have an product with that number."); return;
        }
        /*if (a == null || o == null || p == null)
            { System.out.println("One or more of the details doesn't exist."); return; }*/
        if (a.is_closed)
            { System.out.println("Account " + login_id + " is closed."); return; }
        //TODO: Should add consideration to order status? Will continue after answer in forum.


    }

    private void DisplayOrder(){ // TODO figure out if we are creating n LineItems to 1 Order to n Payments or not, Future
        if (connected_user == null){
            System.out.println("No user is connected! Please log in to display order.");
        }
        else {
            System.out.println("Here is the last order you placed:");
            Account acc = getAccount(connected_user.getLogin_id());
            if (acc != null){
                Order order = acc.Orders.get(0);
                order.printDetails();
            }
            else{
                System.out.println("DUBUG PRINT, USER WITH NO ACCOUNT, STINKY PROBLEM");
            }
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
        }
        ((PremiumAccount) acc).prods.add(prod);
        //TODO: LOOK FOR CLARIFICATION ON FORUM OR MAIL OR NEW VERSION OF ASSIGNMENT DOC
    }

    private void AddProduct(String product_name, String supplier_name){
        Supplier s = getSupplier(supplier_name);
        if(s != null){
            Product p = new Product(product_name,s);
            ProductList.add(p);
            s.Products.add(p);
        }
        else{
            System.out.println("No suppliers with that name exist.");
        }
    }

    private void ShowAllObjects() {
        System.out.println("Accounts:");
        for (Account account : AccountList) {
            System.out.println(account._id);
        }
        System.out.println("Customers:");
        for (Customer customer : CustomerList) {
            System.out.println(customer._id);
        }
        System.out.println("Line Items:");
        for (LineItem lineItem : LineItemList) {
            System.out.println(lineItem._id);
        }
        System.out.println("Orders:");
        for (Order order : OrderList) {
            System.out.println(order._id);
        }
        System.out.println("Payments:");
        for (Payment payment : PaymentList) {
            System.out.println(payment._id);
        }
        System.out.println("Products:");
        for (Product product : ProductList) {
            System.out.println(product._id);
        }
        System.out.println("Shopping Carts:");
        for (ShoppingCart shoppingCart : ShoppingCartList) {
            System.out.println(shoppingCart._id);
        }
        System.out.println("Suppliers:");
        for (Supplier supplier : SupplierList) {
            System.out.println(supplier._id);
        }
        System.out.println("Users:");
        for (User user : UserList) {
            System.out.println(user.login_id);
        }
    }

    private void ShowObjectId(String id){
        System.out.println("Please choose the object you want to see:");
        System.out.println("""
                1) Account
                2) Customer
                3) Line Item
                4) Order
                5) Payment
                6) Product
                7) Shopping Cart
                8) Supplier
                9) User""");
        int pick = scanner.nextInt();
        boolean flag = false;
        switch (pick){//TODO FINISH THE PRINT CONNECTED FUNCTION FOR ALL THE CLASSES
            case 1:
                for (Account account : AccountList) {
                    if(Objects.equals("account" + id, account._id)) {
                        flag = true;
                        System.out.println("Id: "+ account.id+"\nbilling address: " + account.billing_address + "\nIs closed: " + account.is_closed
                        + "\nOpen: "+account.open+"\nClosed: "+ account.closed +"\nBalance: "+account.balance);
                    }
                }
            case 2:
                for (Customer customer : CustomerList) {
                    if (Objects.equals("customer" + id, customer._id)) {
                        flag = true;
                        System.out.println("Id: "+ customer.id+"\nAdress: "+ customer.address+"\nPhone: "+ customer.phone
                        +"\nEmail: "+customer.email);
                    }
                }
            case 3:
                for (LineItem lineItem : LineItemList) {
                    if (Objects.equals("lineItem" + id, lineItem._id)) {
                        flag = true;
                        System.out.println("Quantity: "+ lineItem.quantity+"\nPrice: "+ lineItem.price);
                    }
                }
            case 4:
                for (Order order : OrderList) {
                    if (Objects.equals("order" + id, order._id)) {
                        flag = true;
                        System.out.println("Number: "+ order.number+"\nOrdered: " + order.ordered + "\nShipped: " + order.shipped
                                + "\nShip to: "+order.ship_to+"\nStatus: "+ order.status +"\nTotal: "+order.total);
                    }
                }
            case 5://TODO CHECK IF DELAYED OR IMMEDIATE TO PRINT THEIR ADDITIONAL VARIABLES
                for (Payment payment : PaymentList) {
                    if (Objects.equals("payment" + id, payment._id)) {
                        flag = true;
                        System.out.println("Id: "+ payment.id+"\nPaid: " + payment.paid + "\nTotal: " + payment.total
                                + "\nDetails: "+payment.details);
                    }
                }
            case 6:
                for (Product product : ProductList) {
                    if (Objects.equals("product" + id, product._id)) {
                        flag = true;
                        System.out.println(product._id+"\nName: " + product.name);
                    }
                }
            case 7:
                for (ShoppingCart shoppingCart : ShoppingCartList) {
                    if (Objects.equals("shoppingCart" + id, shoppingCart._id)) {
                        flag = true;
                        System.out.println("Created: " + shoppingCart.created);
                    }
                }
            case 8:
                for (Product product : ProductList) {
                    if (Objects.equals("product" + id, product._id)) {
                        flag = true;
                        System.out.println(product._id+"\nName: " + product.name);
                    }
                }
            case 9:
                for (User user : UserList) {
                    if (Objects.equals("user" + id, user._id)) {
                        flag = true;
                        System.out.println("Login id: "+ user.login_id+"\nPassword: " + user.password +
                                "\nState: " + user.state);
                    }
                }
        }
        if(!flag)
            System.out.println("Id not found please try again later");
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
     * @param id ID of the Customer
     * @return The Customer with the matching ID if exists, else null
     */
    private static Customer getCustomer(String id){
        for (Customer c : CustomerList){
            if (c.id.equals(id)){
                return c;
            }
        }
        return null;
    }
    /**
     * @param id _ID of the LineItem
     * @return The LineItem with the matching ID if exists, else null
     */
    private static LineItem getLineItem(String id){
        for (LineItem l : LineItemList){
            if (l._id.equals(id)){
                return l;
            }
        }
        return null;
    }

    /**
     * @param id ID of the Order
     * @return The Order with the matching ID if exists, else null
     */
    private static Order getOrder(String id){
        for (Order o: OrderList){
            if (o.number.equals(id)){
                return o;
            }
        }
        return null;
    }

    /**
     * @param name ID of the Payment
     * @return The Payment with the matching ID if exists, else null
     */
    private static Payment getPayment(String id){
        for (Payment p : PaymentList){
            if (p._id.equals(id)){
                return p;
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
     * @param name ID of the ShoppingCart
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
    private static void PopulateSystem(){
        SupplierList.add(new Supplier("Osem", "Osem"));
        SupplierList.add(new Supplier("EastWest", "EastWest"));
        ProductList.add(new Product("Bamba", getSupplier("Osem")));
        ProductList.add(new Product("Ramen", getSupplier("EastWest")));
        //AccountList.add(new Account("Dani", "Dani123", getAccount("EastWest"))); TODO figure out if this needs a Customer to reference
        // TODO figure out previous thing and by that logic understand how to deal with PremiumAccount
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
     * Creates a Account with the user's given information.
     * @param c Customer to connect to.
     * @param login_id Given ID.
     * @return Account
     */
    private Account createAccount(Customer c, String login_id){
        System.out.println("Creating account (ID: " + login_id + "):");
        System.out.println("Enter billing address: ");
        String billingaddress = scanner.nextLine();
        System.out.println("Enter initial balance: ");
        int balance = scanner.nextInt();
        String premium;
        while(true){
            System.out.println("Would you like to sign up to our Premium Account program? (y/n)");
            premium = scanner.nextLine();
            if (premium.equalsIgnoreCase("y"))
                return new PremiumAccount(c, login_id, billingaddress, balance);
            else if (premium.equalsIgnoreCase("n")) {
                return new Account(c, login_id, billingaddress, balance);
            }
            else
                System.out.println("Invalid input.");
        }
    }

    /**
     * Creates a ShoppingCart with the user's given information.
     * @param u User to connect to.
     * @param a Account to connect to.
     * @return ShoppingCart
     */
    private ShoppingCart createShoppingCart(User u, Account a){
        return new ShoppingCart(u, a);
    }

}
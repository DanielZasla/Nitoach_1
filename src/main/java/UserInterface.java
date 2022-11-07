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
            System.out.printf("Uh oh, theres already someone called %s%n",login_id);
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

    private void DisplayOrder(){

    }

    private void LinkProduct(String product_name, int price, int quantity){

    }

    private void AddProduct(String product_name, String supplier_name){

    }

    private void ShowAllObjects() {

    }

    private void ShowObjectId(String id){

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

}
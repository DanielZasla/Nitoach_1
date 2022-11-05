import java.util.Vector;

public class UserInterface {
    static Vector<Account> AccountList = new Vector<>();
    static Vector<Customer> CustomerList = new Vector<>();
    static Vector<Product> ProductList = new Vector<>();
    static Vector<Supplier> SupplierList = new Vector<>();
    static Vector<User> UserList = new Vector<>();

    private boolean connected = false;
    private String connected_id;


    public static void main(String[] args) {
        PopulateSystem();
    }

    private void AddUser(String login_id){

    }

    private void RemoveUser(String login_id){

    }

    private void LoginUser(String login_id, String password){
        if (connected){
            System.out.println("A different user is connected right now.");
            return;
        }
        User user;
        if ((user = getUser(login_id)) != null){
            if (user.password.equals(password)) {
                connected = true;
                connected_id = login_id;
                return;
            }
        }
        System.out.println("Login details are incorrect.");
    }

    private void CreateOrder(String address){

    }

    private void AddProduct(String order_id, String login_id, String product_name){

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

    // Private Methods for Vector Functionality

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
     * @param id ID of the Product
     * @return The Product with the matching ID if exists, else null
     */
    private static Product getProduct(String id){
        for (Product p : ProductList){
            if (p.id.equals(id)){
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
        ProductList.add(new Product("Bamba", "Bamba", getSupplier("Osem")));
        ProductList.add(new Product("Ramen", "Ramen", getSupplier("EastWest")));
        //AccountList.add(new Account("Dani", "Dani123", getAccount("EastWest"))); TODO figure out if this needs a Customer to reference
        // TODO figure out previous thing and by that logic understand how to deal with PremiumAccount
    }

}
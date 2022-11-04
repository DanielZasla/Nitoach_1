import java.util.Vector;

public class UserInterface {
    static Vector<Account> AccountList = new Vector<>();
    static Vector<Customer> CustomerList = new Vector<>();
    static Vector<Payment> PaymentList = new Vector<>();
    static Vector<Product> ProductList = new Vector<>();
    static Vector<Supplier> SupplierList = new Vector<>();
    static Vector<User> UserList = new Vector<>();

    private boolean connected = false;
    private String connected_id;

    public static void main(String[] args) {
        SupplierList.add(new Supplier("Osem", "Osem"));
        SupplierList.add(new Supplier("EastWest", "EastWest");
//        ProductList.add(new Product("Bamba", "Bamba", getSupplier("Osem"))));

    }


    private void AddUser(String login_id) {

    }

    private void RemoveUser(String login_id){

    }



    private void LoginUser(String login_id, String password){
        if (connected)
        System.out.println("A different user is connected right now."); return;
        if (getUser(login_id))
            if (getUser(login_id).password.equals(password)) {
                connected = true;
                connected_id = login_id;
                return;
            }
        System.out.println("Login details are incorrect.");
    }

    private void CreateOrder(String address){

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

    private static Supplier getSupplier(String id){
        for (Supplier s : SupplierList){
            if (s.id.equals(id)){
                return s;
            }
        }
        return null;
    }
}

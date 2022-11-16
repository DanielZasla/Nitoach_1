public class User {
    static int UserCounter = 1;
    String _id;
    String login_id;
    String password;
    UserState state;
    Customer customer;
    ShoppingCart cart;


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public User(String login_id, String password) {
        this._id = String.format("user%d",UserCounter);
        UserCounter++;
        this.login_id = login_id;
        this.password = password;
        state = UserState.New;
        customer = null;
        cart = null;
    }

    public String getLogin_id() {
        return login_id;
    }

    public String toString() {
        return "Login id: "+ login_id+"\nPassword: " + password +
                "\nState: " + state.toString();
    }
    public void printConnected(){
        System.out.println("This User is connected to: "+ customer._id);
        System.out.println("This User is also connected to: "+ cart._id);
    }
}

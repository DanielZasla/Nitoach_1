public class Customer {
    String id;
    Address address;
    String phone;
    String email;
    User user;

    public Customer(String id, Address address, String phone, String email) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

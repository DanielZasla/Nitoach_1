public class Customer {
    static int CusCounter = 1;
    String _id;
    String id;
    Address address;
    String phone;
    String email;

    User user;
    Account account;

    public Customer(String id, Address address, String phone, String email) {
        this._id = String.format("customer%d",CusCounter);
        CusCounter++;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Account getAccount() { return account; }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "Id: "+ id+"\nAddress: "+ address.address+"\nPhone: "+ phone
                +"\nEmail: "+email;
    }
    public void printConnected(){
        System.out.println("This Customer is connected to: "+ account._id);
        System.out.println("This Customer is also connected to: "+ user._id);
    }
}

import java.util.Date;

public abstract class Payment {
    static int PayCounter = 1;
    String _id;
    Date paid;
    float total;
    String details;
    Order order;
    Account acc;


    public String toString(){
        return "Id: "+  _id+"\nPaid: " + paid + "\nTotal: " + total
                + "\nDetails: "+ details;

    }
    public void printConnected(){
        System.out.println("This Payment is connected to: "+ order._id);
        System.out.println("This Payment is also connected to: "+ acc._id);
    }
}

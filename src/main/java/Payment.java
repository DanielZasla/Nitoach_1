import java.util.Date;

public abstract class Payment {
    static int PayCounter;
    String _id;
    String id;
    Date paid;
    float total;
    String details;
    Order order;
    Account acc;

    public String getId(){
        return id;
    }
    public String getDetails(){
        return details;
    }
    public float getTotal(){
        return total;
    }
    public Date getPaid(){
        return paid;
    }
    public void setId(String newid){
        id = newid;
    }
    public void setTotal(float newTotal){
        total = newTotal;
    }
    public void setPaid(Date newpaid){
        paid = newpaid;
    }
    public void setDetails(String newdetails){
        details = newdetails;
    }
    public void printConnected(){
        System.out.println("this Payment is connected to: "+ order._id);
        System.out.println("this Payment is also connected to: "+ acc._id);
    }
}

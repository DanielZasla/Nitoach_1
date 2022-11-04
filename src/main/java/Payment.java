import java.util.Date;

public abstract class Payment {
    String id;
    Date paid;
    float total;
    String details;

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
}

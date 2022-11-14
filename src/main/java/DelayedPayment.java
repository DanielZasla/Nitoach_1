import java.util.Date;

public class DelayedPayment extends Payment{
    Date paymentDate;
    public DelayedPayment(){
        this._id = String.format("payment%d",PayCounter);
        PayCounter++;
        Date now  = new Date();
        this.paymentDate = new Date(now.getTime()+ (long)1000*(long)3600*24*28);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Payment Date is: "+paymentDate.toString());
    }
}

import java.util.Date;

public class DelayedPayment extends Payment{
    Date paymentDate;
    public DelayedPayment(){
        this._id = String.format("payment%d",PayCounter);
        PayCounter++;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Payment Date is: "+paymentDate);
    }
}

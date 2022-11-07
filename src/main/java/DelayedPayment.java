import java.util.Date;

public class DelayedPayment extends Payment{
    Date paymentDate;
    public DelayedPayment(){
        this._id = String.format("payment%d",PayCounter);
        PayCounter++;
    }

}

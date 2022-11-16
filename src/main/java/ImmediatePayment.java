import java.util.Date;

public class ImmediatePayment extends Payment{
    boolean phoneConfirmation = false;
    public ImmediatePayment(){
        this._id = String.format("payment%d",PayCounter);
        paid = new Date();
        PayCounter++;

    }

    @Override
    public String toString() {
        return super.toString() + "\nPhone confirmation: " + (phoneConfirmation? "yes": "no");
    }
}

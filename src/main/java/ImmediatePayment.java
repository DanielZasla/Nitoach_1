public class ImmediatePayment extends Payment{
    boolean phoneConfirmation;
    public ImmediatePayment(){//fill this shit
        this._id = String.format("payment%d",PayCounter);
        PayCounter++;
        //do more plz
    }

}

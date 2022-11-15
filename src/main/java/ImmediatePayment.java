public class ImmediatePayment extends Payment{
    boolean phoneConfirmation;
    public ImmediatePayment(){//fill this shit
        this._id = String.format("payment%d",PayCounter);
        PayCounter++;
        //do more plz
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Phone confirmation: " + (phoneConfirmation? "yes": "no"));
    }
}

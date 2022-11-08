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
        if(phoneConfirmation) {
            System.out.println("Is there phone confirmation : yes");
        }
        else {
            System.out.println("Is there phone confirmation : no");
        }
    }
}

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Order {
    static int OrdCounter = 1;
    ArrayList<LineItem> LItems = new ArrayList<>();
    ArrayList<Payment> Payments = new ArrayList<>();
    String _id;
    String number;
    Date ordered;
    Date shipped;
    Address ship_to;
    OrderStatus status;
    Account account;
    float total;

    public Order( Address ship_to, Account acc) {
        this._id = String.format("order%d", OrdCounter);
        this.number = String.valueOf(OrdCounter);
        OrdCounter++;
        this.ship_to = ship_to;
        this.status = OrderStatus.New;
        this.total = 0;
        this.account = acc;
        ordered = new Date();
    }

    public void printDetails() {
        System.out.println("ID number: " + this.number + "\nOrder date: " + this.ordered.toString() + "\nShipping date: " + this.shipped + "\nOrder address: " + this.ship_to.address + "\nOrder status: " + this.status + "\nTotal payed: " + this.total + "$");
    }

    public Payment addItem(LineItem item){
        System.out.println("Immediate payment(default) or delayed payment? (I/D)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        Payment p;
        if (answer.equalsIgnoreCase("d"))
            p = new DelayedPayment();
        else {
            p = new ImmediatePayment();
            account.balance -= item.price;
        }

        p.order = this;
        p.acc = this.account;

        Payments.add(p);
        this.account.Payments.add(p);
        LItems.add(item);

        return p;
    }


    public void printConnected(){
        System.out.println("This Order is connected to these Line Items:");
        for (LineItem item:LItems) {
            System.out.println(item._id);
        }
        System.out.println("This Order is also connected to these Payments:");
        for (Payment payment:Payments) {
            System.out.println(payment._id);
        }
    }

    public ArrayList<Payment> getPayments() {
        return Payments;
    }
}


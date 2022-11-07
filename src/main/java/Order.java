import java.util.Date;

public class Order {
    static int OrdCounter = 1;
    String _id;
    String number;
    Date ordered;
    Date shipped;
    Address ship_to;
    OrderStatus status;
    float total;

    public Order(String number, Address ship_to, float total) {
        this._id = String.format("order%d", OrdCounter);
        OrdCounter++;
        this.number = number;
        this.ship_to = ship_to;
        this.status = OrderStatus.New;
        this.total = total;
    }

    public void showOrder() {
        System.out.println("ID number: " + this.number + "\nOrder date: " + this.ordered + "\nShipping date: " + this.shipped + "\nOrder address: " + this.ship_to + "\nOrder status: " + this.status + "\nTotal payed: " + this.total + "$");

    }
}


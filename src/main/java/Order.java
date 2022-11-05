import java.util.Date;

public class Order {
    String number;
    Date ordered;
    Date shipped;
    Address ship_to;
    OrderStatus status;
    float total;

    public Order(String number, Address ship_to, float total) {
        this.number = number;
        this.ship_to = ship_to;
        this.status = OrderStatus.New;
        this.total = total;
    }
}

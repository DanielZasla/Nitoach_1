import java.util.Date;

public class Order {
    String number;
    Date ordered;
    Date shipped;
    Address ship_to;
    OrderStatus status;
    float total=0;
}

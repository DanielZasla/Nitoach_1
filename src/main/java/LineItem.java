public class LineItem {
    static int LineItemCounter = 1;
    String _id;
    int quantity;
    int price;
    Order order;
    Product product;
    ShoppingCart shoppingCart;

    public LineItem(Product p) {
        this._id = String.format("lineItem%d",LineItemCounter);
        LineItemCounter++;
        this.quantity = p.quantity;
        this.price = p.price;
        this.product = p;
    }

    public String toString() {
        return "Quantity: "+ quantity+"\nPrice: "+ price;
    }
    public void printConnected(){
        System.out.println("This Line Item is connected to: "+ order._id);
        System.out.println("This Line Item is also connected to: "+ product._id);
        System.out.println("This Line Item is also connected to: "+ shoppingCart._id);
    }
}

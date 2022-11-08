public class LineItem {
    static int LineItemCounter = 1;
    String _id;
    int quantity;
    int price;
    Order order;
    Product product;
    ShoppingCart cart;

    public LineItem(int quantity, int price, Product product) {
        this._id = String.format("lineItem%d",LineItemCounter);
        LineItemCounter++;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public void printConnected(){
        System.out.println("this Line Item is connected to: "+ order._id);
        System.out.println("this Line Item is also connected to: "+ product._id);
        System.out.println("this Line Item is also connected to: "+ cart._id);
    }
}

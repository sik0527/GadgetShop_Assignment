public class Gadget {
    private String model;
    private double price;
    private int weight;
    private String size;

    public Gadget(String model, double price, int weight, String size) {
        this.model = model;
        this.price = price;
        this.weight = weight;
        this.size = size;
    }

    // Accessor methods
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public int getWeight() { return weight; }
    public String getSize() { return size; }

    // Display method
    public void display() {
        System.out.println("Model: " + model);
        System.out.println("Price: £" + price);
        System.out.println("Weight: " + weight + " g");
        System.out.println("Size: " + size);
    }
}

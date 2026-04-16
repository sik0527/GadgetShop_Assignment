public class MP3 extends Gadget {
    private int memory;

    // Constructor
    public MP3(String model, double price, int weight, String size, int memory) {
        super(model, price, weight, size);
        this.memory = memory;
    }

    // Accessor
    public int getMemory() {
        return memory;
    }

    // Download music
    public void downloadMusic(int amount) {
        if (amount <= memory) {
            memory -= amount;
        } else {
            System.out.println("Not enough memory.");
        }
    }

    // Delete music
    public void deleteMusic(int amount) {
        memory += amount;
    }

    // Display
    @Override
    public String display() {
        return super.display() + ", Memory: " + memory + "MB";
    }
}

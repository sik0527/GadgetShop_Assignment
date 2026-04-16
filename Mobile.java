public class Mobile extends Gadget {
    private int callingCredit;

    public Mobile(String model, double price, int weight, String size, int callingCredit) {
        super(model, price, weight, size);
        this.callingCredit = callingCredit;
    }

    public int getCallingCredit() {
        return callingCredit;
    }

    // Add credit method
    public void addCredit(int amount) {
        if (amount > 0) {
            callingCredit += amount;
            System.out.println("Added " + amount + " minutes of credit. Total credit: " + callingCredit);
        } else {
            System.out.println("Please enter a positive amount of credit.");
        }
    }

    // Make call method
    public void makeCall(String phoneNumber, int minutes) {
        if (minutes <= callingCredit) {
            callingCredit -= minutes;
            System.out.println("Calling " + phoneNumber + " for " + minutes + " minutes...");
            System.out.println("Remaining credit: " + callingCredit + " minutes.");
        } else {
            System.out.println("Insufficient credit to make this call.");
        }
    }

    // Display method (overridden)
    @Override
    public void display() {
        super.display();
        System.out.println("Calling Credit Remaining: " + callingCredit + " minutes");
    }
}

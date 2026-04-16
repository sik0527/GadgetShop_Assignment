import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Gadget {
    String model, size;
    double price;
    int weight;

    Gadget(String model, double price, int weight, String size) {
        this.model = model; this.price = price; this.weight = weight; this.size = size;
    }
    String display() {
        return "Model: " + model + ", Price: £" + price + ", Weight: " + weight + "g, Size: " + size;
    }
}

class Mobile extends Gadget {
    int credit;
    Mobile(String m, double p, int w, String s, int c) { super(m, p, w, s); credit = c; }
    String makeCall(String num, int dur) {
        if (dur <= credit) { credit -= dur; return "Call to " + num + " for " + dur + " mins. Remaining credit: " + credit; }
        return "Insufficient credit.";
    }
    @Override
    String display() { return super.display() + ", Credit: " + credit + " mins"; }
}

class MP3 extends Gadget {
    int memory;
    MP3(String m, double p, int w, String s, int mem) { super(m, p, w, s); memory = mem; }
    String download(int size) {
        if (size <= memory) { memory -= size; return size + "MB downloaded. Memory left: " + memory + "MB"; }
        return "Not enough memory.";
    }
    @Override
    String display() { return super.display() + ", Memory: " + memory + "MB"; }
}

public class GadgetShop extends JFrame {
    ArrayList<Gadget> gadgets = new ArrayList<>();

    JTextField modelF = new JTextField(8), priceF = new JTextField(8),
            weightF = new JTextField(8), sizeF = new JTextField(8),
            creditF = new JTextField(8), memoryF = new JTextField(8),
            phoneF = new JTextField(8), durationF = new JTextField(8),
            downloadF = new JTextField(8), indexF = new JTextField("0", 3);

    public GadgetShop() {
        setTitle("Gadget Shop");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Input panel
        JPanel input = new JPanel(new GridLayout(10, 2, 5, 5));
        String[] labels = {"Model", "Price", "Weight", "Size", "Credit (Mobile)", "Memory (MP3)", "Phone (Call)", "Duration (Call)", "Download Size", "Index"};
        JTextField[] fields = {modelF, priceF, weightF, sizeF, creditF, memoryF, phoneF, durationF, downloadF, indexF};
        for (int i = 0; i < labels.length; i++) {
            input.add(new JLabel(labels[i] + ":"));
            input.add(fields[i]);
        }
        add(input, BorderLayout.CENTER);

        // Buttons panel: 2 columns x 3 rows layout (like before)
        JPanel buttons = new JPanel(new GridLayout(3, 2, 10, 10));
        buttons.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addMobile = new JButton("Add Mobile");
        JButton addMP3 = new JButton("Add MP3");
        JButton displayAll = new JButton("Display All");
        JButton makeCall = new JButton("Make Call");
        JButton downloadMusic = new JButton("Download Music");
        JButton clear = new JButton("Clear");

        buttons.add(addMobile);
        buttons.add(addMP3);
        buttons.add(displayAll);
        buttons.add(makeCall);
        buttons.add(downloadMusic);
        buttons.add(clear);

        add(buttons, BorderLayout.SOUTH);

        addMobile.addActionListener(e -> {
            try {
                gadgets.add(new Mobile(modelF.getText(), Double.parseDouble(priceF.getText()),
                        Integer.parseInt(weightF.getText()), sizeF.getText(),
                        Integer.parseInt(creditF.getText())));
                JOptionPane.showMessageDialog(this, "Mobile added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for Mobile.");
            }
        });

        addMP3.addActionListener(e -> {
            try {
                gadgets.add(new MP3(modelF.getText(), Double.parseDouble(priceF.getText()),
                        Integer.parseInt(weightF.getText()), sizeF.getText(),
                        Integer.parseInt(memoryF.getText())));
                JOptionPane.showMessageDialog(this, "MP3 added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for MP3.");
            }
        });

        displayAll.addActionListener(e -> {
            if (gadgets.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No gadgets stored.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < gadgets.size(); i++)
                sb.append("[").append(i).append("] ").append(gadgets.get(i).display()).append("\n");
            JTextArea ta = new JTextArea(sb.toString());
            ta.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(ta), "All Gadgets", JOptionPane.INFORMATION_MESSAGE);
        });

        makeCall.addActionListener(e -> {
            try {
                int i = Integer.parseInt(indexF.getText());
                Gadget g = gadgets.get(i);
                if (g instanceof Mobile m) {
                    String msg = m.makeCall(phoneF.getText(), Integer.parseInt(durationF.getText()));
                    JOptionPane.showMessageDialog(this, msg);
                } else JOptionPane.showMessageDialog(this, "Selected gadget is not a Mobile.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid index or input.");
            }
        });

        downloadMusic.addActionListener(e -> {
            try {
                int i = Integer.parseInt(indexF.getText());
                Gadget g = gadgets.get(i);
                if (g instanceof MP3 m) {
                    String msg = m.download(Integer.parseInt(downloadF.getText()));
                    JOptionPane.showMessageDialog(this, msg);
                } else JOptionPane.showMessageDialog(this, "Selected gadget is not an MP3.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid index or input.");
            }
        });

        clear.addActionListener(e -> {
            for (JTextField f : fields) f.setText("");
            indexF.setText("0");
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GadgetShop::new);
    }
}
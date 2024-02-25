import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame implements ActionListener {
    private JRadioButton thinCrustRadio, regularCrustRadio, deepDishCrustRadio;
    private JComboBox<String> sizeComboBox;
    private JCheckBox pepperoniCheckBox, mushroomsCheckBox, olivesCheckBox, pineappleCheckBox, baconCheckBox, extraCheeseCheckBox;
    private JTextArea orderTextArea;
    private JButton orderButton, clearButton, quitButton;

    public PizzaGUIFrame() {
        setTitle("Pizza Order Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel pizzaPanel = new JPanel();
        pizzaPanel.setLayout(new GridLayout(4, 1));
        pizzaPanel.setBorder(BorderFactory.createTitledBorder("Pizza Options"));


        JPanel crustPanel = new JPanel();
        crustPanel.setBorder(BorderFactory.createTitledBorder("Crust"));
        thinCrustRadio = new JRadioButton("Thin");
        regularCrustRadio = new JRadioButton("Regular");
        deepDishCrustRadio = new JRadioButton("Deep-dish");
        ButtonGroup crustGroup = new ButtonGroup();
        crustGroup.add(thinCrustRadio);
        crustGroup.add(regularCrustRadio);
        crustGroup.add(deepDishCrustRadio);
        crustPanel.add(thinCrustRadio);
        crustPanel.add(regularCrustRadio);
        crustPanel.add(deepDishCrustRadio);
        pizzaPanel.add(crustPanel);


        JPanel sizePanel = new JPanel();
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));
        String[] sizes = {"Small", "Medium", "Large", "Super"};
        sizeComboBox = new JComboBox<>(sizes);
        sizePanel.add(sizeComboBox);
        pizzaPanel.add(sizePanel);


        JPanel toppingsPanel = new JPanel();
        toppingsPanel.setBorder(BorderFactory.createTitledBorder("Toppings"));
        pepperoniCheckBox = new JCheckBox("Pepperoni");
        mushroomsCheckBox = new JCheckBox("Mushrooms");
        olivesCheckBox = new JCheckBox("Olives");
        pineappleCheckBox = new JCheckBox("Pineapple");
        baconCheckBox = new JCheckBox("Bacon");
        extraCheeseCheckBox = new JCheckBox("Extra Cheese");
        toppingsPanel.add(pepperoniCheckBox);
        toppingsPanel.add(mushroomsCheckBox);
        toppingsPanel.add(olivesCheckBox);
        toppingsPanel.add(pineappleCheckBox);
        toppingsPanel.add(baconCheckBox);
        toppingsPanel.add(extraCheeseCheckBox);
        pizzaPanel.add(toppingsPanel);

        add(pizzaPanel, BorderLayout.CENTER);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Order"));

        orderTextArea = new JTextArea(10, 20);
        orderTextArea.setEditable(false);
        orderPanel.add(new JScrollPane(orderTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        orderButton = new JButton("Order");
        orderButton.addActionListener(this);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);
        orderPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(orderPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PizzaGUIFrame pizzaGUIFrame = new PizzaGUIFrame();
            pizzaGUIFrame.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderButton) {
            StringBuilder order = new StringBuilder("=======================\n");
            double basePrice = 0.0;
            if (thinCrustRadio.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Thin Crust", 8.0));
                basePrice += 8.0;
            } else if (regularCrustRadio.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Regular Crust", 10.0));
                basePrice += 10.0;
            } else if (deepDishCrustRadio.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Deep-dish Crust", 12.0));
                basePrice += 12.0;
            }
            order.append(String.format("%-35s $%.2f%n", sizeComboBox.getSelectedItem(), basePrice));

            double toppingsPrice = 0.0;
            if (pepperoniCheckBox.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Pepperoni", 1.0));
                toppingsPrice += 1.0;
            }
            if (mushroomsCheckBox.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Mushrooms", 1.0));
                toppingsPrice += 1.0;
            }
            if (olivesCheckBox.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Olives", 1.0));
                toppingsPrice += 1.0;
            }
            if (pineappleCheckBox.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Pineapple", 1.0));
                toppingsPrice += 1.0;
            }
            if (baconCheckBox.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Bacon", 1.0));
                toppingsPrice += 1.0;
            }
            if (extraCheeseCheckBox.isSelected()) {
                order.append(String.format("%-35s $%.2f%n", "Extra Cheese", 1.0));
                toppingsPrice += 1.0;
            }

            double subTotal = basePrice + toppingsPrice;
            double tax = subTotal * 0.07;
            tax = Math.round(tax * 100.0) / 100.0;
            double total = subTotal + tax;

            order.append("-----------------------------------------\n");
            order.append(String.format("%-35s $%.2f%n", "Sub-total:", subTotal));
            order.append(String.format("%-35s $%.2f%n", "Tax:", tax));
            order.append("-----------------------------------------\n");
            order.append(String.format("%-35s $%.2f%n", "Total:", total));
            order.append("=======================");

            orderTextArea.setText(order.toString());
        } else if (e.getSource() == clearButton) {
            thinCrustRadio.setSelected(false);
            regularCrustRadio.setSelected(false);
            deepDishCrustRadio.setSelected(false);
            sizeComboBox.setSelectedIndex(0);
            pepperoniCheckBox.setSelected(false);
            mushroomsCheckBox.setSelected(false);
            olivesCheckBox.setSelected(false);
            pineappleCheckBox.setSelected(false);
            baconCheckBox.setSelected(false);
            extraCheeseCheckBox.setSelected(false);
            orderTextArea.setText("");
        } else if (e.getSource() == quitButton) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
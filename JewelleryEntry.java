package src;

import javax.swing.JTextField;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class JewelleryEntry extends JFrame {
    private JTextField txtsku; // SKU
    private JTextField txtname;
    private JTextField txtbrand;
    private JTextField txtorigin;
    private JTextField txtmaterial;
    private JTextField txtweight;
    private JTextField txttype;
    private JCheckBox checkAvail;

    private JButton cmdSave;
    private JButton cmdClose;

    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    private JewelleryEntry thisForm;
    private JewelleryListing jewelleryListing;

    // declares JTextField for SKU, Name, Brand, Origin, Material, Weight, Price

    /**
     * 
     * @param jl
     */

    public JewelleryEntry(JewelleryListing jl) {
        jewelleryListing = jl;
        thisForm = this;

        setTitle("Entering new jewellery");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlCommand.setBackground(Color.white);
        pnlDisplay.setBackground(Color.white);

        pnlDisplay.add(new JLabel("SKU: "));
        txtsku = new JTextField(20);
        pnlDisplay.add(txtsku);

        pnlDisplay.add(new JLabel("Name:"));
        txtname = new JTextField(20);
        pnlDisplay.add(txtname);

        pnlDisplay.add(new JLabel("Brand: "));
        txtbrand = new JTextField(20);
        pnlDisplay.add(txtbrand);

        pnlDisplay.add(new JLabel("Origin: "));
        txtorigin = new JTextField(20);
        pnlDisplay.add(txtorigin);

        pnlDisplay.add(new JLabel("Type: "));
        txttype = new JTextField(20);
        pnlDisplay.add(txttype);

        pnlDisplay.add(new JLabel("Material: "));
        txtmaterial = new JTextField(20);
        pnlDisplay.add(txtmaterial);

        pnlDisplay.add(new JLabel("Weight: "));
        txtweight = new JTextField(20);
        pnlDisplay.add(txtweight);

        pnlDisplay.add(new JLabel("Available: "));
        checkAvail = new JCheckBox("");
        checkAvail.setBackground(Color.WHITE);
        pnlDisplay.add(checkAvail);

        pnlDisplay.setLayout(new GridLayout(5, 3));

        // Creates a button for "Save"
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/save.png"));
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        cmdSave = new JButton("Save");
        cmdSave.setIcon(resizedIcon);
        cmdSave.setBackground(Color.WHITE);
        cmdSave.addActionListener(new SaveButtonListener());

        // Creates a button for "Close"
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/resources/close.png"));
        Image img1 = icon1.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(img1);
        cmdClose = new JButton("Close");
        cmdClose.setIcon(resizedIcon1);
        cmdClose.setBackground(Color.WHITE);
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    // creates a button that closes the entry screen
    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            thisForm.setVisible(false);
        }
    }

    // creates a button that saves the entry to the GUI table
    private class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String sku = txtsku.getText();
                String name = txtname.getText();
                String brand = txtbrand.getText();
                String type = txttype.getText();
                String origin = txtorigin.getText();
                String material = txtmaterial.getText(); // convert to lower case
                int weight = Integer.parseInt(txtweight.getText());
                boolean available = checkAvail.isSelected();

                double price = 0.0;
                if (material.equalsIgnoreCase("gold")) { // compare with lowercase material types
                    price = weight * 65.00;
                } else if (material.equalsIgnoreCase("silver")) {
                    price = weight * 0.80;
                } else if (material.equalsIgnoreCase("platinum")) {
                    price = weight * 33.00;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid material entered. Please enter 'gold', 'silver', or 'platinum'.");
                    return;
                }

                String formattedPrice = String.format("%.2f", price); // formats it to two decimal places
                double finalPrice = Double.parseDouble(formattedPrice); // converts it back to double

                Jewellery j = new Jewellery(sku, name, brand, type, origin, material, weight, finalPrice, available);
                jewelleryListing.addJewellery(j);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("jewellery.dat").getFile(), true))) {
                    writer.write(sku + " " + name + " " + brand + " " + type + " " + origin + " " + material + " "
                            + weight + " "
                            + formattedPrice + " " + available); // the format in which it is added to the jewellery.dat
                                                                 // file
                    writer.newLine();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error occurred while adding to file");
                }

                JOptionPane.showMessageDialog(null, "Jewellery Entry Saved");

                txtsku.setText("");
                txtname.setText("");
                txtbrand.setText("");
                txttype.setText("");
                txtorigin.setText("");
                txtmaterial.setText("");
                txtweight.setText("");
                checkAvail.setSelected(false);

            } catch (NumberFormatException ex) {
            }
        }
    }
}

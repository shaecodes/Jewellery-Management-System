package src;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;

/**
 * @author Stephanie Williams
 * @author Roshae Sinclair
 * @author Shamoya Thompson
 * @author Jevaun Forbes
 */
public class JewelleryListing extends JPanel {
    private JButton cmdAddJewellery; // a button that adds jewellery
    private JButton cmdSortbyName; // a button that sorts jewellery by name
    private JButton cmdSortbyPrice; // a button that sorts jewellery by price
    private JButton cmdSortbyOrigin; // a button that sorts jewellery by origin
    private JButton cmdSortbyType; // a button that sorts jewellery by type
    private JButton cmdEditAvail; // a button that edits the availability
    private JButton cmdClose; //
    private JButton cmdDelete;

    private JPanel pnlCommand; // panel to store the command buttons

    private JTable table; // list of jewellery
    private ArrayList<Jewellery> jlist;
    private JewelleryListing thisForm; // creates an instance of the jewellerylisting
    private JScrollPane scrollPane; // allows table to be scrolled through
    private DefaultTableModel model;

    public JewelleryListing() {
        super(new GridLayout(2, 1));
        thisForm = this;

        pnlCommand = new JPanel(); // buttom half with commands
        pnlCommand.setBackground(Color.white);

        jlist = loadJewellery(getClass().getResource("/jewellery.dat").getPath());
        String[] attributes = { "SKU",
                "Name",
                "Brand",
                "Type", "Origin",
                "Material", "Weight (g)", "Price ($)", "Availablity" };
        model = new DefaultTableModel(attributes, 0);
        table = new JTable(model);
        table.setBackground(Color.white);
        showTable(jlist);

        table.setPreferredScrollableViewportSize(new Dimension(450, 250));

        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        // Creates a button for "Add Jewellery"
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/resources/add.png"));
        Image img1 = icon1.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(img1);
        cmdAddJewellery = new JButton("Add Jewellery");
        cmdAddJewellery.addActionListener(new AddJewelleryButtonListener());
        cmdAddJewellery.setIcon(resizedIcon1);
        cmdAddJewellery.setBackground(Color.WHITE);
        pnlCommand.add(cmdAddJewellery);

        // Creates a button for "Sort Jewellery by Name"
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/resources/sortname.png"));
        Image img2 = icon2.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(img2);
        cmdSortbyName = new JButton("Sort by Name");
        cmdSortbyName.addActionListener(new SortNameButtonListener());
        cmdSortbyName.setIcon(resizedIcon2);
        cmdSortbyName.setBackground(Color.WHITE);
        pnlCommand.add(cmdSortbyName);

        // Creates a button for "Sort Jewellery by Price"
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/resources/sortprice.png"));
        Image img3 = icon3.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(img3);
        cmdSortbyPrice = new JButton("Sort by Price");
        cmdSortbyPrice.addActionListener(new SortPriceButtonListener());
        cmdSortbyPrice.setIcon(resizedIcon3);
        cmdSortbyPrice.setBackground(Color.WHITE);
        pnlCommand.add(cmdSortbyPrice);

        // Creates a button for "Sort Jewellery by Origin"
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/resources/sortorigin.png"));
        Image img4 = icon4.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon4 = new ImageIcon(img4);
        cmdSortbyOrigin = new JButton("Sort by Origin");
        cmdSortbyOrigin.addActionListener(new SortOriginButtonListener());
        cmdSortbyOrigin.setIcon(resizedIcon4);
        cmdSortbyOrigin.setBackground(Color.WHITE);
        pnlCommand.add(cmdSortbyOrigin);

        // Creates a button for "Sort Jewellery by Type"
        ImageIcon icon5 = new ImageIcon(getClass().getResource("/resources/sorttype.png"));
        Image img5 = icon5.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(img5);
        cmdSortbyType = new JButton("Sort by Type");
        cmdSortbyType.addActionListener(new SortTypeButtonListener());
        cmdSortbyType.setIcon(resizedIcon5);
        cmdSortbyType.setBackground(Color.WHITE);
        pnlCommand.add(cmdSortbyType);

        // Creates a button for "Edit Availability of Jewellery"
        ImageIcon icon6 = new ImageIcon(getClass().getResource("/resources/edit.png"));
        Image img6 = icon6.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon6 = new ImageIcon(img6);
        cmdEditAvail = new JButton("Edit Availability");
        cmdEditAvail.addActionListener(new EditAvailabilityButtonListener());
        cmdEditAvail.setIcon(resizedIcon6);
        cmdEditAvail.setBackground(Color.WHITE);
        pnlCommand.add(cmdEditAvail);

        // Creates a button for "Delete Jewellery"
        ImageIcon icon8 = new ImageIcon(getClass().getResource("/resources/delete.png"));
        Image img8 = icon8.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon8 = new ImageIcon(img8);
        cmdDelete = new JButton("Delete");
        cmdDelete.addActionListener(new DeleteButtonListener());
        cmdDelete.setIcon(resizedIcon8);
        cmdDelete.setBackground(Color.WHITE);
        pnlCommand.add(cmdDelete);

        // Creates a button for "Close"
        ImageIcon icon7 = new ImageIcon(getClass().getResource("/resources/close.png"));
        Image img7 = icon7.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon7 = new ImageIcon(img7);
        cmdClose = new JButton("Close");
        cmdClose.addActionListener(new CloseButtonListener());
        cmdClose.setIcon(resizedIcon7);
        cmdClose.setBackground(Color.RED);
        pnlCommand.add(cmdClose);

        add(pnlCommand);
    }

    /**
     * 
     * @param jlist
     */
    private void showTable(ArrayList<Jewellery> jlist) // shows the table on the GUI
    {
        if (jlist.size() > 0) {
            for (int i = 0; i < jlist.size(); i++) {
                addToTable(jlist.get(i));
            }
        }
    }

    /**
     * 
     * @param j
     */

    public void addJewellery(Jewellery j) // adds jewellery to list
    {
        jlist.add(j);
        addToTable(j);
    }

    /**
     * 
     * @param j
     */
    private void addToTable(Jewellery j) // adds jewellery to GUI table from jList
    {
        String[] item = { j.getSKU(), "" + j.getName(), "" + j.getBrand(), "" + j.getType(), "" + j.getOrigin(),
                "" + j.getMaterial(), "" + j.getWeight(), "" + j.getPrice(), "" + j.isAvailable() };
        model.addRow(item);
    }

    // a method that loads a Jewellery from a file and adds to the jlist.
    /**
     * 
     * @param jfile
     * @return
     */
    private ArrayList<Jewellery> loadJewellery(String jfile) {
        Scanner jscan = null;
        ArrayList<Jewellery> jlist = new ArrayList<Jewellery>();
        try {
            jscan = new Scanner(new File(jfile));
            while (jscan.hasNext()) {
                String[] nextLine = jscan.nextLine().split(" ");
                if (nextLine.length != 9) {
                    continue;
                }

                String sku = nextLine[0];
                String name = nextLine[1];
                String brand = nextLine[2];
                String type = nextLine[3];
                String origin = nextLine[4];
                String material = nextLine[5];
                int weight;
                double price;
                boolean available;

                try {
                    weight = Integer.parseInt(nextLine[6]);
                    price = Double.parseDouble(nextLine[7]);
                } catch (NumberFormatException e) {
                    continue;
                }

                // ignores the case of the true or false entered
                if (nextLine[8].equalsIgnoreCase("true")) {
                    available = true;
                } else if (nextLine[8].equalsIgnoreCase("false")) {
                    available = false;
                } else {
                    continue;
                }

                Jewellery j = new Jewellery(sku, name, brand, type, origin, material, weight, price, available);
                jlist.add(j);
            }
            jscan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (NumberFormatException e) {
            System.out.println("Number Format Error");
        }
        return jlist;
    }

    // a main driver
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showGUI();
            }
        });
    }

    // a method that creates the GUI screen
    private static void showGUI() {
        JFrame frame = new JFrame("Jewellery Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JewelleryListing newContentPane = new JewelleryListing();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    // a method for a button to sort by Name
    private class SortNameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Collections.sort(jlist, new Comparator<Jewellery>() {
                public int compare(Jewellery j1, Jewellery j2) {
                    return j1.getName().compareTo(j2.getName());
                }
            });
            model.setRowCount(0);
            showTable(jlist);
        }
    }

    // a method for a button to sort by Price
    class SortPriceButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            Collections.sort(jlist, new Comparator<Jewellery>() {
                public int compare(Jewellery j1, Jewellery j2) {
                    Double price1 = j1.getPrice();
                    Double price2 = j2.getPrice();
                    return price1.compareTo(price2);
                }
            });
            model.setRowCount(0);
            showTable(jlist);
        }
    }

    // a method for a button to sort by Origin
    private class SortOrigin implements Comparator<Jewellery> {
        public int compare(Jewellery j1, Jewellery j2) {
            return j1.getOrigin().compareTo(j2.getOrigin());
        }
    }

    private class SortOriginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            model.setRowCount(0);
            Collections.sort(jlist, new SortOrigin());
            showTable(jlist);
        }
    }

    // a method for a button to sort by Type
    private class SortTypeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Collections.sort(jlist, new Comparator<Jewellery>() {
                public int compare(Jewellery j1, Jewellery j2) {
                    return j1.getType().compareTo(j2.getType());
                }
            });
            model.setRowCount(0);
            showTable(jlist);
        }
    }

    // a button to add a Jewellery
    private class AddJewelleryButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JewelleryEntry jentry = new JewelleryEntry(thisForm);
        }
    }

    // a button to remove a Jewellery
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int x = table.getSelectedRow();
            if (x == -1) {
                JOptionPane.showMessageDialog(thisForm, "Please select a row");
            } else {
                model.removeRow(x);
                JOptionPane.showMessageDialog(null,
                        "Jewellery Deleted");
            }
        }
    }

    // a button to close
    private class CloseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            System.exit(0);
        }
    }

    // edit the availability of a jewellery by asking the user to select the row to
    // be deleted
    /*
     * references:
     * https://www.youtube.com/watch?v=Tg62AxNRir4
     * https://www.youtube.com/watch?v=RRxJuSCY2SI
     */
    private class EditAvailabilityButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = table.getSelectedRow(); // collects the selected row
            if (row == -1) {
                JOptionPane.showMessageDialog(thisForm, "Please select a row.");
                // if no row / index is selected. An error message will come up
            } else {
                // the getValueat returns a object therefore it is converted to a string
                String name = (String) table.getValueAt(row, 1);
                boolean available = Boolean.parseBoolean((String) table.getValueAt(row, 8));

                String availabilityString;
                if (available) {
                    availabilityString = "true";
                } else {
                    availabilityString = "false";
                }

                String newAvailabilityString = JOptionPane.showInputDialog(thisForm,
                        "Enter New availability for " + name + " by entering \"true\" or \"false\":",
                        availabilityString);

                if (newAvailabilityString != null) {
                    try {
                        boolean newAvailability = Boolean.parseBoolean(newAvailabilityString);
                        model.setValueAt(newAvailability, row, 8); // accepts the new value, the index and the column to
                                                                   // be edited
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(thisForm, "Invalid input. Enter \"true\" or \"false\".");
                    }
                }
            }
        }
    }
}

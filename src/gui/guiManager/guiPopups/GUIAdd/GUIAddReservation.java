package gui.guiManager.guiPopups.GUIAdd;

import entities.Item;
import entities.Product;
import gui.GUIMain;
import gui.guiManager.GUIObject;
import gui.guiManager.guiPopups.GUIGetProduct;
import managers.MainManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GUIAddReservation extends GUIObject
{
    //constants
    private final String TITLE = "Nowa Rezerwacja";

    //JButton
    JButton buttonAddItem = new JButton("Dodaj Egzemplarz");
    JButton buttonRemoveItem = new JButton("Usuń Egzemplarz");
    JButton buttonSubmit = new JButton("Usuń Egzemplarz");

    //JLabel
    private JLabel labelClientId = new JLabel("ID klienta:");
    private JLabel labelDateStart = new JLabel("Data początku: (yyyy-mm-dd)");
    private JLabel labelDateEnd = new JLabel("Data końca: (yyyy-mm-dd)");

    //JTextField
    private JTextField textFieldClientId = new JTextField(10);
    private JTextField textFieldDateStart = new JTextField(10);
    private JTextField textFieldDateEnd = new JTextField(10);

    //panelViewList
    ArrayList<Product> cartProducts = new ArrayList<>();
    PanelViewList panelViewList = new PanelViewList();

    //methods
    public GUIAddReservation(Window parent)
    {
        super(parent);
        addActionListeners();
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+100, GUIMain.HEIGHT_WINDOW + 250, TITLE);
    }

    @Override
    public void addActionListeners()
    {
        buttonSubmit.addActionListener(this);
        buttonAddItem.addActionListener(this);
        buttonRemoveItem.addActionListener(this);
    }

    @Override
    public void initComponents()
    {
        buttonSubmit.setText("OK");
        textFieldClientId.setMinimumSize(new Dimension(120, 20));
        textFieldDateStart.setMinimumSize(new Dimension(120, 20));
        textFieldDateEnd.setMinimumSize(new Dimension(120, 20));
        textFieldDateStart.setText(String.valueOf(LocalDate.now(ZoneId.of("Poland"))));
        textFieldDateEnd.setText(String.valueOf(LocalDate.now(ZoneId.of("Poland")).plusDays(7)));

        //layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets =  new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelClientId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(textFieldClientId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(panelViewList, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(buttonAddItem, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(buttonRemoveItem, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelDateStart, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(textFieldDateStart, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelDateEnd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(textFieldDateEnd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.ipadx = 100;
        panel.add(buttonSubmit, gbc);

        panelViewList.updateView();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if (source == buttonSubmit)
        {
            if (textFieldClientId.getText().equals("") ||
                    textFieldDateStart.getText().equals("") ||
                    textFieldDateEnd.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Wypełnij puste pola!");
                return;
            }
            if(cartProducts.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Koszyk jest pusty!");
                return;
            }
            try
            {
                List<Item> itemList = new Vector<>();
                for(Product product : cartProducts)
                {
                    for(Item item : product.getItems())
                        if(item.getId_reservation() == -2)
                            itemList.add(item);
                }

                if(MainManager.getInstance().searchClient(Integer.parseInt(textFieldClientId.getText())) == null)
                {
                    JOptionPane.showMessageDialog(this, "Nie znaleziono klienta!");
                    return;
                }

                MainManager.getInstance().addReservation(Integer.parseInt(textFieldClientId.getText()),
                        new SimpleDateFormat("yyyy-MM-dd").parse(textFieldDateStart.getText()),
                        new SimpleDateFormat("yyyy-MM-dd").parse(textFieldDateEnd.getText()),
                        itemList);

                dispose();
            }
            catch(NumberFormatException | ParseException ex)
            {
                JOptionPane.showMessageDialog(this,
                        "Wypelnij poprawne dane!", "Wypelnij poprawne dane!", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(source == buttonAddItem)//OK
        {
            addProductToCart(GUIGetProduct.getProduct(this));
            panelViewList.updateView();
        }
        else if(source == buttonRemoveItem)
        {
            int selectedIndex = panelViewList.getSelectedIndex();
            Product selectedProduct = cartProducts.get(selectedIndex);

            if(cartProducts.contains(selectedProduct))  //warunek bycia w koszyku
            {
                selectedProduct.returnInCart();
                if(selectedProduct.getNumberOfReservedItems() == 0) //jesli usunieto ostatni egzemplarz
                {
                    cartProducts.remove(selectedIndex);
                }
                panelViewList.updateView();
            }
        }
    }

    @Override
    public void initDialog(int width, int height, String title)
    {
        super.initDialog(width, height, title);
    }

    //

    private class PanelViewList extends JScrollPane
    {
        private static final long serialVersionUID = 1L;

        private JTable table;
        private DefaultTableModel tableModel;

        PanelViewList()
        {
            setMinimumSize(new Dimension(300, 200));
            String[] header = {"ID", "nazwa", "cena", "ilość"};
            tableModel = new DefaultTableModel(header, 0);
            setBorder(BorderFactory.createTitledBorder("Egzemplarze w koszyku:"));

            table = new JTable(tableModel)
            {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int col)
                {
                    return false;
                }
            };
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setRowSelectionAllowed(true);
            setViewportView(table);
        }

        void updateView()
        {
            tableModel.setRowCount(0);
            for (Product p : cartProducts)
            {
                if (p != null)
                {
                    String[] row = {Integer.toString(p.getId()), p.getName(), Float.toString(p.getPrice()), Integer.toString(p.getNumberOfReservedItems())};
                    tableModel.addRow(row);
                }
            }
//		table.setModel(tableModel);
        }

        int getSelectedIndex()
        {
            int index = table.getSelectedRow();
            if (index < 0)
            {
                JOptionPane.showMessageDialog(this, "Zaznacz wiersz.", "Błąd.", JOptionPane.ERROR_MESSAGE);
            }
            return index;
        }
    }

    private void addProductToCart(Product product)
    {
        if(product.isAvailable())
        {
            if(!cartProducts.contains(product))
                cartProducts.add(product);
            product.reserveInCart();
        }else
        {
            JOptionPane.showMessageDialog(this, "Brak dostępnych egzemplarzy.", "Błąd.", JOptionPane.ERROR_MESSAGE);
        }
    }
}

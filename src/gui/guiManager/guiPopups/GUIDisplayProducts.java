package gui.guiManager.guiPopups;

import entities.Product;
import gui.GUIMain;
import gui.guiManager.GUIObject;
import managers.MainManager;
import managers.ProductManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUIDisplayProducts extends GUIObject
{
    //constants
    private final String TITLE = "Wyświetlanie produktów";

    //panelViewList
    ArrayList<Product> cartProducts = new ArrayList<>();
    GUIDisplayProducts .PanelViewList panelViewList = new GUIDisplayProducts .PanelViewList();

    //methods
    public GUIDisplayProducts (Window parent)
    {
        super(parent);
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+100, GUIMain.HEIGHT_WINDOW + 150, TITLE);
    }

    @Override
    public void initComponents()
    {
        MainManager.getInstance();

        for (Product product: ProductManager.getInstance().productList)
            addProductToCart(product);

        //layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets =  new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(panelViewList, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        panelViewList.updateView();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

    @Override
    public void addActionListeners() {

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
            String[] header = {"ID", "nazwa", "cena", "liczba egzemplarzy", "liczba dostępnych egzemplarzy"};
            tableModel = new DefaultTableModel(header, 0);
            setBorder(BorderFactory.createTitledBorder("Produkty:"));

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
                    String[] row = {Integer.toString(p.getId()), p.getName(), Float.toString(p.getPrice()),
                            Integer.toString(p.getItemListSize()), Integer.toString(p.getNumberOfAvailableItems())};
                    tableModel.addRow(row);
                }
            }
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

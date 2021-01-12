package gui.guiManager.guiPopups.GUIProducts;

import entities.Product;
import gui.GUIMain;
import gui.guiManager.GUIObject;
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
    PanelViewList panelViewList = new PanelViewList();

    //methods
    public GUIDisplayProducts (Window parent)
    {
        super(parent);
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+400, GUIMain.HEIGHT_WINDOW + 200, TITLE);
    }

    @Override
    public void initComponents()
    {
        for(Product product: ProductManager.getInstance().getProductList())
            if(!cartProducts.contains(product))
                cartProducts.add(product);

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
    public void addActionListeners()
    {

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
            setMinimumSize(new Dimension(600, 300));
            String[] header = {"ID", "nazwa", "cena[zł]", "liczba egzemplarzy", "liczba dostępnych egzemplarzy"};
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
                    String[] row = {Integer.toString(p.getId()), p.getName(), String.format("%.2f", p.getPrice()),
                            Integer.toString(p.getItemListSize()), Integer.toString(p.getNumberOfAvailableItems())};
                    tableModel.addRow(row);
                }
            }
        }
    }
}

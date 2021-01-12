package gui.guiManager.guiPopups.GIUDelete;

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

public class GUIDeleteProduct extends GUIObject
{
    //constants
    private final String TITLE = "Usuwanie produktu";

    //JButton
    JButton buttonRemoveProduct = new JButton("Usuń Egzemplarz");

    //panelViewList
    ArrayList<Product> cartProducts = new ArrayList<>();
    GUIDeleteProduct.PanelViewList panelViewList = new GUIDeleteProduct.PanelViewList();

    //methods
    public GUIDeleteProduct(Window parent)
    {
        super(parent);
        addActionListeners();
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+100, GUIMain.HEIGHT_WINDOW + 150, TITLE);
    }

    @Override
    public void addActionListeners()
    {
        buttonRemoveProduct.addActionListener(this);
    }

    @Override
    public void initComponents()
    {
        MainManager.getInstance();

        for (Product product: ProductManager.getInstance().getProductList())
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
        panel.add(buttonRemoveProduct, gbc);

        panelViewList.updateView();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == buttonRemoveProduct)
        {
            int selectedIndex = panelViewList.getSelectedIndex();
            Product selectedProduct = cartProducts.get(selectedIndex);

            if(cartProducts.contains(selectedProduct))
            {
                MainManager.getInstance().removeProduct(selectedProduct.getId());
                cartProducts.remove(selectedIndex);
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
            String[] header = {"ID", "nazwa", "cena"};
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
                    String[] row = {Integer.toString(p.getId()), p.getName(), Double.toString(p.getPrice())};
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
}

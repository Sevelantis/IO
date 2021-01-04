package gui.guiManager.guiPopups.GUIAdd;

import entities.Product;
import gui.GUIMain;
import gui.guiManager.GUIObject;
import gui.guiManager.guiPopups.GUIGetProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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

    //JTextField
    private JTextField textFieldClientId = new JTextField(10);

    //panelViewList
    ArrayList<Product> cartProducts = new ArrayList<>();
    PanelViewList panelViewList = new PanelViewList();

    //methods
    public GUIAddReservation(Window parent)
    {
        super(parent);
        addActionListeners();
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+100, GUIMain.HEIGHT_WINDOW + 150, TITLE);
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

        //fake populate todo
//        ProductManager.getInstance().add(new Product("Kostium hipopotama", (float)9.99));
//        cartProducts.add(ProductManager.getInstance().get(0));
        cartProducts.add(new Product("Kostium hipopotama", (float)9.99));
        cartProducts.add(new Product("Kostium nosorożca", (float)93.99));
        cartProducts.add(new Product("Kostium Dartha Vadera z czerwonym mieczem świetlnym", (float)955.99));

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
        gbc.gridwidth = 2;
        gbc.ipadx = 100;
        panel.add(buttonSubmit, gbc);

        //todo
        panelViewList.updateView();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == buttonSubmit)
        {
            System.out.println("buttonSubmit");
        }
        else if(source == buttonAddItem)//OK
        {
            cartProducts.add(GUIGetProduct.getProduct(this));
            panelViewList.updateView();
        }
        else if(source == buttonRemoveItem)
        {
            cartProducts.remove(panelViewList.getSelectedIndex());
            panelViewList.updateView();
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
            setMinimumSize(new Dimension(300, 200));//todo
            String[] header = {"ID", "nazwa", "cena"};
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
                    String[] row = {Integer.toString(p.getId()), p.getName(), Float.toString(p.getPrice())};
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

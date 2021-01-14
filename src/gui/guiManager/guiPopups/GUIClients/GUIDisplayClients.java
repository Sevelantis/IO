package gui.guiManager.guiPopups.GUIClients;

import entities.Client;
import gui.GUIMain;
import gui.guiManager.GUIObject;
import managers.ClientManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUIDisplayClients extends GUIObject
{
    //constants
    private final String TITLE = "Wyświetlanie rezerwacji";

    //panelViewList
    ArrayList<Client> clientList = new ArrayList<>();
    PanelViewList panelViewList = new PanelViewList();

    //methods
    public GUIDisplayClients (Window parent)
    {
        super(parent);
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+400, GUIMain.HEIGHT_WINDOW + 200, TITLE);
    }

    @Override
    public void initComponents()
    {
        for(Client client: ClientManager.getInstance().getClientList())
            if(!clientList.contains(client))
                clientList.add(client);

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
            setMinimumSize(new Dimension(450, 300));
            String[] header = {
                    "ID klienta",
                    "Imię i Nazwisko",
                    "nr telefonu",
                    "email"
            };
            tableModel = new DefaultTableModel(header, 0);
            setBorder(BorderFactory.createTitledBorder("Rezerwacje:"));

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
            for (Client c : clientList)
            {
                if (c != null)
                {
                    String clientData = c.getFirstName() + " " + c.getLastName();
                    String[] row = {
                            Integer.toString(c.getId()),
                            clientData,
                            c.getPhoneNr(),
                            c.getEmail()
                    };
                    tableModel.addRow(row);
                }
            }
        }
    }

}

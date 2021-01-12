package gui.guiManager.guiPopups.GUIReservations;

import entities.Client;
import entities.Reservation;
import gui.GUIMain;
import gui.guiManager.GUIObject;
import managers.ClientManager;
import managers.ReservationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GUIDisplayReservations extends GUIObject
{
    //constants
    private final String TITLE = "Wyświetlanie rezerwacji";

    //panelViewList
    ArrayList<Reservation> reservationList = new ArrayList<>();
    PanelViewList panelViewList = new PanelViewList();

    //methods
    public GUIDisplayReservations (Window parent)
    {
        super(parent);
        initComponents();
        initDialog(GUIMain.WIDTH_WINDOW+400, GUIMain.HEIGHT_WINDOW + 200, TITLE);
    }

    @Override
    public void initComponents()
    {
        for(Reservation reservation: ReservationManager.getInstance().getReservationList())
            if(!reservationList.contains(reservation))
                reservationList.add(reservation);

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
            String[] header = {
                    "ID rezerwacji",
                    "ID klienta",
                    "Imię i Nazwisko",
                    "cena[zł]",
                    "data początku",
                    "data końca",
                    "status"};
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
            for (Reservation r : reservationList)
            {
                if (r != null)
                {
                    Client c = ClientManager.getInstance().get(r.getId_client());
                    String clientData = c.getFirstName() + " " + c.getLastName();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String[] row = {
                            Integer.toString(r.getId()),
                            Integer.toString(r.getId_client()),
                                    clientData,
                            String.format("%.2f", r.getPrice()),
                            dateFormat.format(r.getDateStart()),
                            dateFormat.format(r.getDateEnd()),
                            String.valueOf(r.getStatus())
                            };
                    tableModel.addRow(row);
                }
            }
        }
    }
}

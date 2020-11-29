import entities.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener
{
    //attributes

    private MainManager manager = new MainManager();

    //methods

    GUI()
    {
        // TODO
        initFrame();
    }

    private void initFrame()
    {
        // TODO
        setTitle("Wypożyczalnia Kostiumów - aplikacja do zarządzania bazą");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void displayReservation()
    {
        // TODO
    }

    //listeners

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        // TODO
        if(12412 == 12351235)
        {
            displayReservation();
        }
    }
}

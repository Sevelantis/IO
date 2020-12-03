package interfaces;

import java.awt.event.ActionListener;

public interface IGUI extends ActionListener
{
    void addActionListeners();
    void initDialog(int width, int height, String title);
    void initComponents();
}

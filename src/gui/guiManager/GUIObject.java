package gui.guiManager;

import interfaces.IGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class GUIObject extends JDialog implements IGUI
{
    //constants
    private static final long serialVersionUID = 1L;

    //attributes
    Window parent;

    //JPanel
    protected JPanel panel = new JPanel();

    //JButton
    protected JButton buttonAdd = new JButton();//for add purposes
    protected JButton buttonSearch = new JButton();//for search purposes

    //methods
    protected GUIObject(Window parent)
    {
        super(parent, ModalityType.DOCUMENT_MODAL);
        this.parent = parent;
    }

    //IGUIManager
    @Override
    public abstract void actionPerformed(ActionEvent e);

    @Override
    public abstract void addActionListeners();

    @Override
    public abstract void initComponents();

    @Override
    public void initDialog(int width, int height, String title)
    {
        setContentPane(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(width, height));
        setLocationRelativeTo(parent);
        setTitle(title);
        setVisible(true);
    }
}

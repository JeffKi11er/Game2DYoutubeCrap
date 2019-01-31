package com.android.Frame;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FrameBall extends JFrame implements WindowListener {
    //private static int W = 800;
    //private static int H = 600;

    public FrameBall() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(10,10,700,800);
        //setSize(W, H);
        setResizable(false);
        setTitle("GameBall");
        addWindowListener(this);
        setLocationRelativeTo(null);
        PanelBall panelBall = new PanelBall();
        add(panelBall);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

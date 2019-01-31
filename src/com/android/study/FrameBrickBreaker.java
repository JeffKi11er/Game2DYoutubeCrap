package com.android.study;

import com.android.study.Panel.PanelBrickBreaker;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FrameBrickBreaker extends JFrame implements WindowListener {
    public static int Width = 800;
    public static int Heigth = 700;
    public FrameBrickBreaker() {
        setResizable(false);
        setTitle("Game Brick Breaker");
        setLocationRelativeTo(null);
        setBounds(0,0,Width+7,Heigth);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(this);
        PanelBrickBreaker panelBrickBreaker = new PanelBrickBreaker();
        add(panelBrickBreaker);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(null,"Do you want to exit ?","Exit Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
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

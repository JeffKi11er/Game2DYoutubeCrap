package com.android;

import Game.Game2D;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private Canvas canvas;
    private Thread thread;
    private boolean running;
    public Panel() {
        setLayout(null);
        setBackground(Color.BLACK);
        canvas = new Canvas();
        add(canvas);
        Thread thread = new Thread();
        thread.start();

    }

}

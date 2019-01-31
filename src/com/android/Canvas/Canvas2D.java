package com.android.Canvas;

import java.awt.*;

public class Canvas2D extends Canvas {
    private static int W = 800;
    private static int H = 600;
    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(new Dimension(W,H));
        setMaximumSize(new Dimension(W,H));
        setMinimumSize(new Dimension(W,H));
    }
}

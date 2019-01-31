package com.android.Frame;

import Map.Map_Generator;
import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PanelBall extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;//bc screen start over game shouldn't play by itself
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;//speech
    private int x_player = 310;
    private int x_ball = 120;
    private int y_ball = 350;
    private int ball_xdir = -1;//X_direction
    private int ball_ydir = -2;//Y_direction
    private Map_Generator mapGenerator;

    public PanelBall() {
        mapGenerator = new Map_Generator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        //draw map
        mapGenerator.draw((Graphics2D) g);
        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font(null, Font.BOLD, 25));
        g.drawString("" + score, 590, 30);
        //border
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        //the paddle
        g.setColor(Color.GREEN);
        g.fillRect(x_player, 550, 100, 8);
        //the ball
        g.setColor(Color.YELLOW);
        g.fillOval(x_ball, y_ball, 20, 20);
        if (totalBricks <= 0) {
            play = false;
            ball_xdir = 0;
            ball_ydir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font(null, Font.BOLD, 30));
            g.drawString("You Won", 260, 300);
        }
        if (y_ball > 570) {
            play = false;
            ball_xdir = 0;
            ball_ydir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font(null, Font.BOLD, 30));
            g.drawString("Game over , Score:" + score, 190, 300);

            g.setFont(new Font(null, Font.BOLD, 25));
            g.drawString("Press Enter to restart", 230, 350);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(x_ball, y_ball, 20, 20).intersects(new Rectangle(x_player, 550, 100, 8))) {
                ball_ydir = -ball_ydir;
            }
            //create value for each brick
            A:
            for (int i = 0; i < mapGenerator.map.length; i++) {
                for (int j = 0; j < mapGenerator.map[0].length; j++) {
                    if (mapGenerator.map[i][j] > 0) {
                        int brickx = j * mapGenerator.brickWidth + 80;
                        int bricky = i * mapGenerator.brickHeight + 50;
                        int brick_W = mapGenerator.brickWidth;
                        int brick_H = mapGenerator.brickHeight;
                        Rectangle rectangle = new Rectangle(brickx, bricky, brick_W, brick_H);
                        Rectangle reg_ball = new Rectangle(x_ball, y_ball, 20, 20);
                        Rectangle bickrect = rectangle;
                        if (reg_ball.intersects(bickrect)) {
                            mapGenerator.setBrickValue(0, i, j);
                            score += 5;
                            totalBricks--;

                            if (x_ball + 19 <= bickrect.x || x_ball + 1 >= bickrect.x + bickrect.width) {//to check if ball hit left or right
                                ball_xdir = -ball_xdir;//of one brick
                            } else {//check if the ball hit on top or bottom of a brick
                                ball_ydir = -ball_ydir;
                            }
                            break A;//break Laybel
                        }
                    }
                }
            }
            x_ball += ball_xdir;
            y_ball += ball_ydir;
            if (y_ball < 0) {
                ball_ydir = -ball_ydir;
            }
            if (x_ball > 670) {
                ball_xdir = -ball_xdir;
            }
            if (x_ball < 0) {
                ball_xdir = -ball_xdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (x_player >= 600) {
                x_player = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (x_player < 10) {
                x_player = 10;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                x_ball = 120;
                y_ball = 350;
                ball_xdir = -1;
                ball_ydir = -2;
                x_player = 310;
                score = 0;
                totalBricks = 21;
                mapGenerator = new Map_Generator(3, 7);
                repaint();
            }
        }
    }

    private void moveRight() {
        play = true;
        x_player += 20;
    }

    private void moveLeft() {
        play = true;
        x_player -= 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

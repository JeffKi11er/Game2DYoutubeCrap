package com.android.study.Panel;

import com.android.study.MapGame.Bricks;

import static com.android.study.FrameBrickBreaker.Width;
import static com.android.study.FrameBrickBreaker.Heigth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelBrickBreaker extends JPanel implements ActionListener, KeyListener {
    Bricks bricks;
    private int ball_x = 450;
    private int ball_y = 450;
    private int playerX = Width / 2 - 60;
    private int playerY = Heigth - 50;
    private int playerWidth = 150;
    private int playerHeight = 15;
    private int x_dir = -2;
    private int y_dir = -3;
    private Timer timer;
    private int Score;
    private int TotalBrick = 40;
    private int delay = 1;
    private boolean play = false;

    public PanelBrickBreaker() {
        bricks = new Bricks(4, 10);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(playerX, playerY, playerWidth, playerHeight).intersects(new Rectangle(ball_x, ball_y, 10, 10))) {//solve the errors
                if (ball_x + 9 <= playerX || ball_x + 1 >= playerX + playerWidth) {
                    x_dir = -x_dir;
                } else if (ball_y < playerY) {
                    y_dir = -y_dir;
                }else
                    y_dir += 1;
            }
            A:
            for (int i = 0; i < bricks.map.length; i++) {
                for (int j = 0; j < bricks.map[0].length; j++) {
                    if (bricks.map[i][j] > 0) {//need this to identify a brick
                        int brick_W = bricks.brickWidth;
                        int brick_H = bricks.brickHeight;
                        int brickX = j * brick_W + 75;
                        int brickY = i * brick_H + 40;
                        Rectangle ballR = new Rectangle(ball_x, ball_y, 10, 10);
                        Rectangle brick = new Rectangle(brickX, brickY, brick_W, brick_H);
                        Rectangle bricK = brick;
                        if (bricK.intersects(ballR)) {//need this other while ball will move horizontally
                            bricks.value(0, i, j);
                            Score += 5;
                            TotalBrick--;
                            if (ball_x + 13 <= bricK.x || ball_x + 1 >= bricK.x + brick_W) {
                                x_dir = -x_dir;
                            } else {
                                y_dir = -y_dir;
                            }
                            break A;
                        }
                    }
                }
            }
            if (ball_x < 0) {
                x_dir = -x_dir;
            }
            if (ball_x > 770) {
                x_dir = -x_dir;
            }
            if (ball_y < 0) {
                y_dir = -y_dir;
            }
            ball_x += x_dir;
            ball_y += y_dir;
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ///background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, Width - 4, Heigth - 6);
        ///map
        bricks.draw((Graphics2D) g);
        ///border
        g.setColor(Color.RED);
        g.fillRect(0, 0, 3, Heigth - 3);
        g.fillRect(1, 0, Width - 3, 3);
        g.fillRect(Width - 3, 0, 3, Heigth - 3);
        ///ball
        g.setColor(Color.YELLOW);
        g.fillOval(ball_x, ball_y, 10, 10);
        ///player
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(playerX, playerY, playerWidth, playerHeight);
        if (TotalBrick <= 0) {
            play = false;
        }
        if (ball_y > 770) {
            play = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 630) {
                playerX = 630;
            } else
                moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 5) {
                playerX = 5;
            } else
                moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ball_x = 450;
                ball_y = 450;
                x_dir = -2;
                y_dir = -3;
                playerX = Width / 2 - 60;//need to be under x dir, y dir
                TotalBrick = 40;
                Score = 0;
                bricks = new Bricks(4, 10);
                repaint();
            }
        }
    }

    private void moveLeft() {
        play = true;
        playerX -= 20;
    }

    private void moveRight() {
        play = true;
        playerX += 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package com.gaming.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import com.gaming.snake.utils.GameConstants;

public class GamePanel extends JPanel implements ActionListener {
    private final int[] x = new int[GameConstants.ALL_TILES];
    private final int[] y = new int[GameConstants.ALL_TILES];
    private int bodyParts = 3;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private final Random random;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(
            GameConstants.WIDTH * GameConstants.TILE_SIZE, 
            GameConstants.HEIGHT * GameConstants.TILE_SIZE));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(GameConstants.DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (running) {
            // Draw apple
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);

            // Draw snake
            for (int i = 0; i < bodyParts; i++) {
                g.setColor(i == 0 ? Color.GREEN : new Color(45, 180, 0));
                g.fillRect(x[i], y[i], GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
            }

            // Draw score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + applesEaten, 10, 20);
        } else {
            gameOver(g);
        }
    }

    private void newApple() {
        appleX = random.nextInt(GameConstants.WIDTH) * GameConstants.TILE_SIZE;
        appleY = random.nextInt(GameConstants.HEIGHT) * GameConstants.TILE_SIZE;
    }

    private void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] -= GameConstants.TILE_SIZE;
            case 'D' -> y[0] += GameConstants.TILE_SIZE;
            case 'L' -> x[0] -= GameConstants.TILE_SIZE;
            case 'R' -> x[0] += GameConstants.TILE_SIZE;
        }
    }

    private void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    private void checkCollisions() {
        // Check body collision
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        // Check wall collision
        if (x[0] < 0 || x[0] >= GameConstants.WIDTH * GameConstants.TILE_SIZE ||
            y[0] < 0 || y[0] >= GameConstants.HEIGHT * GameConstants.TILE_SIZE) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    private void gameOver(Graphics g) {
        // Game Over text
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", 
            GameConstants.WIDTH * GameConstants.TILE_SIZE / 4, 
            GameConstants.HEIGHT * GameConstants.TILE_SIZE / 2);

        // Score text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Final Score: " + applesEaten, 
            GameConstants.WIDTH * GameConstants.TILE_SIZE / 4, 
            GameConstants.HEIGHT * GameConstants.TILE_SIZE / 2 + 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    if (direction != 'R') direction = 'L';
                }
                case KeyEvent.VK_RIGHT -> {
                    if (direction != 'L') direction = 'R';
                }
                case KeyEvent.VK_UP -> {
                    if (direction != 'D') direction = 'U';
                }
                case KeyEvent.VK_DOWN -> {
                    if (direction != 'U') direction = 'D';
                }
            }
        }
    }
}

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
        this.addKeyListener(new KeyAdapter());
        startGame();
    }

    // [Rest of the GamePanel implementation...]
}

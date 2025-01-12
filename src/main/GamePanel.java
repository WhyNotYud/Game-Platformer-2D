package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// Vẽ hình ảnh của game
public class GamePanel extends JPanel {
    // Lưu tọa độ của đối tượng
    private float xDelta = 100, yDelta = 100;
    // Lưu hướng di chuyển của đối tượng (Thay đổi theo x và y)
    private float xDir = 0.05f, yDir = 0.05f;
    private Color color;
    private Random random;

    private final MouseInputs mouseInputs;
    private final KeyboardInputs keyboardInputs;

    public GamePanel() {
        random = new Random();
        color = new Color(150, 20, 90);
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRecPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void update() {
        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color = getRandomColor();
        }
        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
            color = getRandomColor();
        }
    }

    public Color getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        update();
        graphics.setColor(color);
        graphics.fillRect((int) xDelta, (int) yDelta, 200, 50);
    }
}

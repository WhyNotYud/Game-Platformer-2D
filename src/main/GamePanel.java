package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

// Vẽ hình ảnh của game
public class GamePanel extends JPanel {
    // Lưu tọa độ của đối tượng
    private float xDelta = 100, yDelta = 100;
    private int animationTick, animationIndex, animationSpeed = 15;

    private final MouseInputs mouseInputs;
    private final KeyboardInputs keyboardInputs;

    private int playerAction = IDLE;
    private int playerDirection = -1;

    private boolean moving = false;

    private BufferedImage img;
    private BufferedImage[][] animations;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Vẽ hình ảnh
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        updateAnimations();
        setAnimations();
        updatePosition();
        graphics.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 112, 70, null);
    }

    // Thiết lập kích thước panel
    public void setPanelSize() {
        Dimension size = new Dimension(1120, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    // Nhập hình ảnh
    public void importImg() {
        try (InputStream is = getClass().getResourceAsStream("/res/player_sprites.png")) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: /res/player_sprites.png");
            }
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("Error reading image", e);
        }
    }

    // Tải hoạt ảnh
    public void loadAnimations() {
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    // Cập nhật hoạt ảnh
    public void updateAnimations() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    // Thiết lập trạng thái hoạt ảnh
    public void setAnimations() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    // Cập nhật vị trí của nhân vật
    public void updatePosition() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    xDelta -= 2;
                    break;
                case RIGHT:
                    xDelta += 2;
                    break;
                case UP:
                    yDelta -= 2;
                    break;
                case DOWN:
                    yDelta += 2;
                    break;
            }
        }
    }

    // Thiết lập hướng di chuyển
    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    // Thiết lập trạng thái di chuyển
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity {
    // Mảng chứa các khung hình hoạt ảnh cho nhân vật
    private BufferedImage[][] animations;
    // Biến để quản lý tốc độ và chỉ số khung hình hoạt ảnh
    private int animationTick, animationIndex, animationSpeed = 30;

    // Biến lưu trữ hành động hiện tại của nhân vật
    private int playerAction = IDLE;
    // Các biến để theo dõi trạng thái di chuyển
    private boolean left, up, right, down;
    // Biến trạng thái di chuyển và tấn công của nhân vật
    private boolean moving = false, attacking = false;
    // Tốc độ di chuyển của nhân vật
    private float playerSpeed = 1.0f;

    // Constructor khởi tạo vị trí và tải hoạt ảnh
    public Player(float x, float y) {
        super(x, y);
        loadAnimations(); // Gọi phương thức để tải hoạt ảnh
    }

    // Cập nhật trạng thái của nhân vật
    public void update() {
        updatePosition(); // Cập nhật vị trí
        updateAnimations(); // Cập nhật hoạt ảnh
        setAnimations(); // Thiết lập trạng thái hoạt ảnh
    }

    // Vẽ nhân vật lên màn hình
    public void render(Graphics graphics) {
        graphics.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 112, 70, null);
    }

    // Tải hoạt ảnh từ tệp hình ảnh
    public void loadAnimations() {
        try (InputStream is = getClass().getResourceAsStream("/res/player_sprites.png")) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: /res/player_sprites.png");
            }
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[9][6];
            // Chia nhỏ hình ảnh thành các khung hình hoạt ảnh
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading image", e);
        }
    }

    // Cập nhật hoạt ảnh dựa trên tick và tốc độ
    public void updateAnimations() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0; // Đặt lại tick
            animationIndex++; // Chuyển sang khung hình tiếp theo
            // Nếu đã đến cuối hoạt ảnh, đặt lại chỉ số khung hình và trạng thái tấn công
            if (animationIndex >= getSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    // Thiết lập trạng thái hoạt ảnh dựa trên di chuyển và tấn công
    public void setAnimations() {
        int startAnimation = playerAction; // Lưu trạng thái trước đó

        // Nếu đang di chuyển, đặt trạng thái là chạy
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE; // Nếu không di chuyển, đặt trạng thái là đứng yên
        }

        // Nếu đang tấn công, đặt trạng thái là tấn công
        if (attacking) {
            playerAction = ATTACK_1;
        }

        // Nếu trạng thái hoạt ảnh thay đổi, đặt lại tick hoạt ảnh
        if (startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    // Đặt lại tick và chỉ số khung hình hoạt ảnh
    public void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    // Cập nhật vị trí của nhân vật dựa trên hướng di chuyển
    public void updatePosition() {
        moving = false; // Đặt trạng thái di chuyển về false
        // Cập nhật vị trí dựa trên hướng di chuyển
        if (left && !right) {
            x -= playerSpeed; // Di chuyển sang trái
            moving = true;
        } else if (right && !left) {
            x += playerSpeed; // Di chuyển sang phải
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed; // Di chuyển lên trên
            moving = true;
        } else if (down && !up) {
            y += playerSpeed; // Di chuyển xuống dưới
            moving = true;
        }
    }

    // Đặt lại tất cả các biến hướng di chuyển về false
    public void resetDirection() {
        left = right = down = up = false;
    }

    // Các phương thức getter và setter cho hướng di chuyển và trạng thái tấn công
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

// Vẽ hình ảnh của trò chơi và xử lý các sự kiện chuột, bàn phím.
public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs; // Đối tượng để xử lý sự kiện chuột
    private Game game; // Tham chiếu đến đối tượng Game

    // Constructor
    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this); // Khởi tạo MouseInputs
        setPanelSize(); // Thiết lập kích thước cho panel
        addKeyListener(new KeyboardInputs(this)); // Thêm lắng nghe sự kiện bàn phím
        addMouseListener(mouseInputs); // Thêm lắng nghe sự kiện chuột
        addMouseMotionListener(mouseInputs); // Thêm lắng nghe sự kiện di chuyển chuột
    }

    // Phương thức vẽ hình ảnh lên panel
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); // Gọi phương thức cha để vẽ nền
        game.render(graphics); // Gọi phương thức render của đối tượng Game để vẽ trò chơi
    }

    // Thiết lập kích thước panel
    public void setPanelSize() {
        Dimension size = new Dimension(1120, 640); // Đặt kích thước cho panel
        setMinimumSize(size); // Kích thước tối thiểu
        setPreferredSize(size); // Kích thước ưa thích
        setMaximumSize(size); // Kích thước tối đa
    }

    // Phương thức cập nhật trò chơi
    public void updateGame() {
        // Cập nhật trạng thái trò chơi nếu cần
    }

    public Game getGame() {
        return game;
    }
}
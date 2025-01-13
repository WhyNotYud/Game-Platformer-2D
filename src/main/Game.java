package main;

import entities.Player;

import java.awt.*;

// Quản lý vòng đời của trò chơi và xử lý các sự kiện.
public class Game implements Runnable {

    private GameWindow gameWindow; // Cửa sổ trò chơi
    private GamePanel gamePanel; // Bảng trò chơi
    private Thread gameThread; // Đối tượng luồng để chạy trò chơi
    private final int FPS_SET = 120; // Số khung hình mong muốn trên mỗi giây
    private final int UPS_SET = 200; // Số cập nhật trạng thái mong muốn trên mỗi giây
    private Player player; // Đối tượng người chơi

    // Constructor
    public Game() {
        initClasses(); // Khởi tạo các lớp
        gamePanel = new GamePanel(this); // Tạo bảng trò chơi
        gameWindow = new GameWindow(gamePanel); // Tạo cửa sổ trò chơi
        gamePanel.requestFocus(); // Nhận sự kiện từ bàn phím và chuột
        start(); // Bắt đầu luồng trò chơi
    }

    // Phương thức khởi tạo các đối tượng trong trò chơi
    public void initClasses() {
        player = new Player(200, 200); // Khởi tạo đối tượng Player tại vị trí (200, 200)
    }

    // Định nghĩa các hành động mà luồng sẽ thực hiện
    @Override
    public void run() {
        // Tính toán thời gian cho mỗi khung hình và cập nhật
        double timePerFrame = (double) 1000000000 / FPS_SET; // Thời gian cho mỗi khung hình
        double timPerUpdate = (double) 1000000000 / UPS_SET; // Thời gian cho mỗi cập nhật
        long lastCheck = System.currentTimeMillis(); // Thời gian kiểm tra FPS/UPS
        long previousTime = System.nanoTime(); // Thời gian trước đó để tính toán
        int frames = 0, updates = 0; // Biến đếm số khung hình và cập nhật
        double deltaU = 0, deltaF = 0; // Biến lưu trữ độ trễ cho cập nhật và vẽ

        while (true) { // Vòng lặp chính của trò chơi
            long currentTime = System.nanoTime(); // Lấy thời gian hiện tại
            deltaU += (currentTime - previousTime) / timPerUpdate; // Cập nhật độ trễ
            deltaF += (currentTime - previousTime) / timePerFrame; // Cập nhật độ trễ cho khung hình
            previousTime = currentTime; // Cập nhật thời gian trước đó

            // Nếu đủ thời gian cho một cập nhật
            if (deltaU >= 1) {
                update(); // Cập nhật trạng thái trò chơi
                updates++; // Tăng biến đếm cập nhật
                deltaU--; // Giảm độ trễ
            }
            // Nếu đủ thời gian cho một khung hình
            if (deltaF >= 1) {
                gamePanel.repaint(); // Vẽ lại bảng trò chơi
                frames++; // Tăng biến đếm khung hình
                deltaF--; // Giảm độ trễ
            }
            // Kiểm tra và in ra FPS và UPS mỗi giây
            if (System.currentTimeMillis() - lastCheck > 1000) {
                lastCheck = System.currentTimeMillis(); // Cập nhật thời gian kiểm tra
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0; // Đặt lại biến đếm khung hình
                updates = 0; // Đặt lại biến đếm cập nhật
            }
        }
    }

    // Cập nhật trạng thái của đối tượng Player
    public void update() {
        player.update();
    }

    // Vẽ đối tượng Player lên bảng trò chơi
    public void render(Graphics g) {
        player.render(g);
    }

    // Tạo ra luồng mới và bắt đầu chạy luồng
    public void start() {
        gameThread = new Thread(this); // Tạo một luồng mới
        gameThread.start(); // Bắt đầu chạy luồng
    }

    public Player getPlayer() {
        return player;
    }

    // Xử lý sự kiện khi cửa sổ mất tiêu điểm
    public void windowFocusLost() {
        player.resetDirection(); // Đặt lại hướng di chuyển của Player
    }
}
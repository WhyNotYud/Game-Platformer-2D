package main;

// Runnalbe: Xử lý đa luồng, cho phép nó được chạy trong một luồng riêng biệt.
public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread; // Đối tượng luồng để chạy trò chơi
    private int FPS_SET = 120; // Số khung hình mong muốn trên mỗi giây

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(); // Nhận sự kiện từ bàn phím và chuột
        start(); // Bắt đầu luồng trò chơi
    }


    ///
    /// Định nghĩa các hành động mà luồng sẽ thực hiện
    /// Tính toán thời gian cần thiết cho mỗi khung hình (timePerFrame) dựa trên FPS mong muốn.
    /// Sử dụng vòng lặp vô hạn (while (true)) để liên tục kiểm tra thời gian.
    /// Khi thời gian đã đủ cho một khung hình, gọi gamePanel.repaint() để cập nhật giao diện.
    /// Cập nhật và in ra số FPS mỗi giây.
    ///
    @Override
    public void run() {
        // Lưu trữ thời lượng mỗi khung hình tính bằng nano giây
        double timePerFrame = (double) 1000000000 / FPS_SET;
        long lastFrame = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        long now;
        int frames = 0;

        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck > 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }

    // Tạo ra luồng mới và bắt đầu chạy luồng
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }
}

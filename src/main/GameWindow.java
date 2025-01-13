package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

// Cửa sổ game
public class GameWindow extends JFrame {
    private final JFrame jFrame;
    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(gamePanel);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        // Lắng nghe sự kiện mất và có lại tiêu điểm của cửa sổ
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            // Mất tiêu điểm
            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }

            // Có lại tiêu điểm
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

        });
    }
}

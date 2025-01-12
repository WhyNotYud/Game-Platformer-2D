package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Xử lý sự kiện bàn phím
public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    ///
    /// Thay đổi vị trí của nhân vật khi bấm các nút W, A, S, D
    /// Các phím W, A, S, D hoặc các phím mũi tên được ánh xạ để di chuyển lên, xuống, trái, phải.
    ///
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                gamePanel.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                gamePanel.changeXDelta(5);
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                gamePanel.changeYDelta(-5);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}

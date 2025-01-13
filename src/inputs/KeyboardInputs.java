package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Xử lý sự kiện bàn phím
public class KeyboardInputs implements KeyListener {
    private final GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(false);
                break;
        }
    }

    /// Thay đổi vị trí của nhân vật khi bấm các nút W, A, S, D
    /// Các phím W, A, S, D hoặc các phím mũi tên được ánh xạ để di chuyển lên, xuống, trái, phải.
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamePanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamePanel.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_W:
                gamePanel.getGame().getPlayer().setUp(true);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


}

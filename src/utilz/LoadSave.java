package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png"; // Tên tệp hình ảnh cho nhân vật
    public static final String LEVEL_ATLAS = "outside_sprites.png"; // Tên tệp hình ảnh cho sprite bên ngoài
    public static final String LEVEL_ONE_DATA = "level_one_data.png"; // Tên tệp hình ảnh cho dữ liệu cấp độ một

    // Phương thức để lấy hình ảnh sprite atlas từ tệp
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    // Phương thức để lấy dữ liệu cấp độ từ hình ảnh
    public static int[][] GetLevelData() {
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        // Duyệt qua từng pixel của hình ảnh
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        return lvlData;
    }
}
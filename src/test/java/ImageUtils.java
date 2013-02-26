/**
 * @Author: caoxiao
 * @Date: 13-1-13 上午11:10
 */
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageUtils {
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    private ImageUtils() {
    }

    public static void pressText(String targetImg, String pressText1,
                                 String pressText2, String pressText3, int fontSize, Color color) {
        try {
            File file = new File(targetImg);
            File dstFile = new File(targetImg + "_dst.jpg");

            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font("宋体", Font.BOLD, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    1));
            int x = 135;
            int y = 163;
            int width_1 = fontSize * getLength(pressText1);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }

            g.drawString(pressText1, x, y + height_1);

            x = 135;
            y = 355;
            width_1 = fontSize * getLength(pressText1);
            height_1 = fontSize;
            widthDiff = width - width_1;
            heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }
            g.drawString(pressText2, x, y + height_1);

            x = 135;
            y = 550;
            width_1 = fontSize * getLength(pressText1);
            height_1 = fontSize;
            widthDiff = width - width_1;
            heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }
            g.drawString(pressText3, x, y + height_1);

            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, dstFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    public static void main(String[] args) throws IOException {
        String taijiongPath = "/Users/apple/Desktop/taijiong.jpg";
        pressText(taijiongPath, "张新伟你在干嘛呢？", "别说话我猜猜？",
                "哈哈哈，你们肯定在买火车票!", 18, Color.WHITE);
    }
}

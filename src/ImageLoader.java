import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    public static Image[] loadImage(String path, int width) throws IOException {
            BufferedImage load = ImageIO.read(ImageLoader.class.getResource(path));
            Image[] images = new Image[load.getHeight() / width];


        for (int i = 0; i < images.length; i++) {
            images[i] = load.getSubimage(i * width, 0, width, width);
        }


        return images;


    }
}

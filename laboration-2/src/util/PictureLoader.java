package util;

import cars.Car;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;

public class PictureLoader {
    BufferedImage[] pics;

    public static BufferedImage getImage(Object c) {
        try {
            return ImageIO.read(new File(getSrcName(c)));
        } catch(IOException e) {
            try {
                return ImageIO.read(new File("src/pics/default.jpg"));
            } catch(IOException p) {
                return null;
            }
        }
    }

    private static String getSrcName(Object o) {
        String name = o.getClass().getSimpleName();
        String src = "src/pics/"+name+".jpg";
        return src;
    }
}

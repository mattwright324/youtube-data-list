package mattw.youtube.datav3;

import com.google.gson.annotations.SerializedName;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

/**
 * @version 2018-12-08
 * @author mattwright324
 */
public class Thumbs implements Serializable {

    @SerializedName("default")
    Thumbnail default_thumb;
    Thumbnail medium;
    Thumbnail high;

    public class Thumbnail implements Serializable {
        URL url;
        int width, height;

        public URL getURL() {
            return url;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public BufferedImage toBufferedImage() throws IOException {
            return ImageIO.read(url);
        }

        public ImageIcon toImageIcon() throws IOException {
            return new ImageIcon(toBufferedImage());
        }
    }

    public Thumbnail getDefault() {
        return default_thumb;
    }

    public Thumbnail getMedium() {
        return medium;
    }

    public Thumbnail getHigh() {
        return high;
    }
}

package mattw.youtube.datav3;

import com.google.gson.annotations.SerializedName;
import mattw.youtube.datav3.list.Thumbnails;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Thumbs {
    @SerializedName("default")
    public Thumbnail default_thumb;
    public Thumbnail medium;
    public Thumbnail high;

    public class Thumbnail {
        public URL url;
        public int width;
        public int height;

        public BufferedImage getBufferedImage() throws IOException {
            return ImageIO.read(url);
        }

        public ImageIcon getImageIcon() throws IOException {
            return new ImageIcon(getBufferedImage());
        }
    }
}

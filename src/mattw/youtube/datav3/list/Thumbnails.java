package mattw.youtube.datav3.list;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.annotations.SerializedName;

public class Thumbnails {
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

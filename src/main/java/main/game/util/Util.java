package main.game.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.game.Config;

public class Util {
	private Util(){
		throw new IllegalAccessError("Util class must not be initialized");
	}
	
	/**
	 * Reads and returns image. Subsequent reads of image with same name are done from cache.
	 * @param fileName
	 * @return image
	 */
	public static Image readImage(String fileName){
		Image image = ImageHolder.getImage(fileName);
		if (image == null){
			String location = String.format("%s%s", Config.IMAGES_FOLDER,fileName).toLowerCase();
			image = null;
			try {
				image = ImageIO.read(Util.class.getResourceAsStream(location));
				//image = ImageIO.read(new File(location));
			} catch (IOException e) {
				throw new IllegalArgumentException(String.format("image not found [got %s]",location));
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(String.format("image not found [got %s]",location));
			}
			ImageHolder.putImage(fileName, image);
		}
		return image;
	}
	
	/**
	 * Creates point with random position within given area.
	 * @param width 
	 * @param height
	 * @return random Point
	 */
	public static Point randomPointInArea(int width, int height){
		if (width < 0 || height < 0){
			throw new IllegalArgumentException("you're trying to fit something "
					+ "too large into gap too small. W:" + width + " H:" + height);
		}
		Random rnd = new Random();
		return new Point(rnd.nextInt(width),rnd.nextInt(height));
	}

	/** Check whether two areas with given points as corners and dimensions as sizes overlap, where one area's 
	 * dimensions are increased by padding (and corner shifted by same amount)
	 * @param areaCorner1 Top left corner of first item
	 * @param areaSize1 dimension of first item
	 * @param areaCorner2 Top left corner of second item
	 * @param areaSize2 dimension of second item
	 * @param padding if areas don't overlap, maximum distance between area borders allowed for true to be returned
	 * @return true if areas overlap
	 */
	public static boolean areasOverlap(Point areaCorner1, Size areaSize1, Point areaCorner2, Size areaSize2, int padding){
		Point _areaCorner2 = new Point(areaCorner2.x-padding,areaCorner2.y-padding);
		Dimension _areaSize2 = new Dimension(areaSize2.width+2*padding,areaSize2.height+2*padding);
		if (areaCorner1.x > _areaCorner2.x - areaSize1.width && areaCorner1.x < _areaCorner2.x + _areaSize2.width){
			if (areaCorner1.y > _areaCorner2.y - areaSize1.height && areaCorner1.y < _areaCorner2.y + _areaSize2.height){
				return true;
			}
		}
		return false;
	}

	/**
	 * Same as graphics.drawImage(image, point.x, point.y, dimension.width, dimension.height, null.
	 * @param graphics
	 * @param image
	 * @param point
	 * @param size
	 */
	public static void drawImage(Graphics graphics, Image image, Point point,
			Size size) {
		graphics.drawImage(image,point.x, point.y, size.width, size.height, null);
		
	}

	/**
	 * Returns point that will help to position area with size of {@code size2} to the center of area {@code size1}.
	 * @param size1	target area size
	 * @param size2 size of item to be placed
	 * @return a point so that second area would be in the center for first area
	 */
	public static Point placeInMiddleOf(Size size1, Size size2) {
		return new Point((size1.width-size2.width)/2,(size1.height-size2.height)/2);
	}

	/**
	 * Draws small white string {@code text} with 1px stroke with top left corner of {@code point}
	 * on Graphics {@code g}.
	 * @param g Graphics to draw on
	 * @param point top left corner of text
	 * @param text string to draw
	 */
	public static void drawTextSmall(Graphics g, Point point, String text) {
		Font f = new Font("Helvetica", Font.PLAIN, 12);
		g.setFont(f);
		g.setColor(Color.BLACK);
		for (int m = -1; m < 2; m+=2){
			for (int n = -1; n < 2; n+=2){
				g.drawString(text, point.x + m, point.y + g.getFont().getSize() + n);
				
			}
		}
		f = new Font("Helvetica", Font.PLAIN, 12);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString(text, point.x, point.y + g.getFont().getSize());
	}

}

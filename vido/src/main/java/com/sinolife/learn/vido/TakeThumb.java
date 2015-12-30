package com.sinolife.learn.vido;

/**
 * @author yucl80@163.com
 *
 */

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.github.hoary.javaav.Demuxer;
import com.github.hoary.javaav.Image;
import com.github.hoary.javaav.MediaFrame;
import com.github.hoary.javaav.VideoFrame;

public class TakeThumb {

	static boolean reading = true;

	public static void main(String[] args) throws Exception {
		String video = "D:/tmp/test.mp4";
		
		Demuxer demuxer = new Demuxer();
		long cur = System.currentTimeMillis();
		demuxer.open(video);
       
		MediaFrame mediaFrame;
		//mediaFrame = demuxer.readFrame();
		System.out.println(System.currentTimeMillis() - cur);
		while (reading && (mediaFrame = demuxer.readFrame()) != null) {
			if (mediaFrame.getType() == MediaFrame.Type.VIDEO) {
				VideoFrame videoFrame = (VideoFrame) mediaFrame;
				BufferedImage image = Image.createImage(videoFrame, BufferedImage.TYPE_3BYTE_BGR);
				System.out.println(System.currentTimeMillis() - cur);
				image = resizeImage(image, 100, 100);
				ImageIO.write(image, "jpg", new File("d:/a.jpg"));
				System.out.println(System.currentTimeMillis() - cur);
				demuxer.close();
				return;
			}
			if (mediaFrame.getType() == MediaFrame.Type.AUDIO) {

			}
		}
		System.out.println(System.currentTimeMillis() - cur);
		demuxer.close();

	}

	public static BufferedImage resizeImage(final BufferedImage bufferedimage,
			final int width, final int height) {
		double Ratio = 0.0;
		java.awt.Image itemp = bufferedimage.getScaledInstance(width, height, BufferedImage.SCALE_FAST);
		if ((bufferedimage.getHeight() > width) || (bufferedimage.getWidth() > height)) {
			if (bufferedimage.getHeight() > bufferedimage.getWidth())
				Ratio = (double) width / bufferedimage.getHeight();
			else
				Ratio = (double) height / bufferedimage.getWidth();
		}
		AffineTransformOp op = new AffineTransformOp(AffineTransform
				.getScaleInstance(Ratio, Ratio), null);
		itemp = op.filter(bufferedimage, null);
		return (BufferedImage) itemp;
	}

}
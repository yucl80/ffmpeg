package com.sinolife.learn.vido;

/**
 * @author yucl80@163.com
 *
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

public class Grabber {

	/**
	 * 获取指定视频的帧并保存为图片至指定目录
	 * @param videofile  源视频文件路径
	 * @param framefile  截取帧的图片存放路径
	 * @throws Exception
	 */
	public static void fetchFrame(String videofile, String framefile)
			throws Exception {
		long start = System.currentTimeMillis();
		File targetFile = new File(framefile);
		FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile); 
		ff.start();
		int lenght = ff.getLengthInFrames();
		int i = 0;
		Frame f = null;
		while (i < lenght) {
			// 过滤前100帧
			f = ff.grabFrame();
			if ((i > 100) && (f.image != null)) {
				break;
			}
			i++;
		}
		Buffer[] img = f.image;
		int owidth = f.imageWidth;
		int oheight = f.imageHeight;
		// 对截取的帧进行等比例缩放
		int width = 270;
		int height = (int) (((double) width / owidth) * oheight);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	/*	BufferToImage bti= new BufferToImage(f.); 
        bi.getGraphics().drawImage(((BufferedImage)img).getScaledInstance(width, height, Image.SCALE_SMOOTH),
                0, 0, null);
		imageIO.write(bi, "jpg", targetFile);*/
		//ff.flush();
		ff.stop();
		System.out.println(System.currentTimeMillis() - start);
	}

	public static void main(String[] args) {
		try {
			Grabber.fetchFrame("E:\\ceshi\\11.mp4", "E:\\ceshi\\test.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
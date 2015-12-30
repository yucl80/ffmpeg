package com.sinolife.learn.vido;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.media.Buffer;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PhotoCatch {
	private Buffer buffer;
	private BufferToImage bufferToImage;
	private Image image;
	private FrameGrabbingControl fgc;
	
	public PhotoCatch(Player player){
		fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
		this.buffer = fgc.grabFrame();
		bufferToImage = new BufferToImage((VideoFormat) buffer.getFormat());
		image = bufferToImage.createImage(this.buffer);
	}
	public void upDate(){
		this.buffer = fgc.grabFrame();
		bufferToImage = new BufferToImage((VideoFormat) buffer.getFormat());
		image = bufferToImage.createImage(this.buffer);
	}
	public int getImageWidth(){
		return image.getWidth(null);
	}
	public int getImageHeight(){
		return image.getHeight(null);
	}
	public void saveImage(String address){
		saveImage(image,address);
	}
	
	private void saveImage(Image image, String path) {
		if(this.image == null)
			System.out.println("image is null");
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(image, null, null);
		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPEGImageEncoder je = JPEGCodec.createJPEGEncoder(fos);
		JPEGEncodeParam jp = je.getDefaultJPEGEncodeParam(bi);
		jp.setQuality(0.5f, false);
		je.setJPEGEncodeParam(jp);
		try {
			je.encode(bi);
			fos.close();
		} catch (ImageFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("错误4");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("错误5");
		}
	}
}

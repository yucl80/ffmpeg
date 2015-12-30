package com.sinolife.learn.vido;

/**
 * @author yucl80@163.com
 *
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.hoary.javaav.Camera;

public class CameraExample {

	static boolean reading = true;

	static BufferedImage img;

	public static void main(String[] args) throws Exception {
		String cameraName = "Lenovo EasyCamera";
		String format = "dshow";

		JFrame frame = new JFrame("Camera Test");
		frame.setSize(640, 480);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				reading = false;
				System.exit(0);
			}
		});
		Panel2 jPanel = new Panel2();
		// jPanel.setLayout(new BorderLayout());
		frame.getContentPane().add(jPanel);
		frame.setVisible(true);

		Camera camera = new Camera(cameraName, format);
		camera.open(640, 480, 25);

		while (reading) {
			System.out.println("do loop");
			// frame.showImage(camera.getImage());
            System.out.println(camera.isOpen());
			img = camera.getImage();
			ImageIO.write(img, "jpg", new File("d:/ax.jpg"));

			jPanel.repaint();

		}

		camera.close();
		// frame.dispose();
	}

}

class Panel2 extends JPanel {
	Panel2() {	
		setPreferredSize(new Dimension(640, 480));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(CameraExample.img, 20, 20, null);
		System.out.println("call paint");
	}
}

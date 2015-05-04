package edu.uchicago.cs.java.finalproject.game.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameFrame extends JFrame {

	private JPanel contentPane;
	private BorderLayout borderLayout1 = new BorderLayout();

	public GameFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			initialize();
//            addImage();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//
//    public void addImage() throws IOException {
//        BufferedImage image = ImageIO.read(new File("src/u1.jpg"));
//        contentPane.add(new JLabel(new ImageIcon(image)));
//        contentPane.updateUI();


//    }

	//Component initialization
	private void initialize() throws Exception {

		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);

	}

	@Override
	//Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}
}

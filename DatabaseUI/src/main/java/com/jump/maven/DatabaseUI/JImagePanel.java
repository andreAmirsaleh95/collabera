package com.jump.maven.DatabaseUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JImagePanel extends JPanel
{
	private Image img;

	public JImagePanel(LayoutManager layout, String imgSrc)
	{
		super(layout);
		img = new ImageIcon(imgSrc).getImage();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
		// TODO Auto-generated constructor stub
	}

	public JImagePanel(String imgSrc)
	{
		img = new ImageIcon(imgSrc).getImage();
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

}

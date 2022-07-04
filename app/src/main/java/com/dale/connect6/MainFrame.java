package com.dale.connect6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import com.dale.connect6.panels.ControlPanel;
import com.dale.connect6.panels.GoBoard;

public class MainFrame extends JFrame {
	private static final String TITLE = "Connect 6";
	public static int monitorWidth;
	public static int monitorHeight;
	
	private GoBoard goBoard;
	private ControlPanel controlPanel;
	private JPanel mainPanel = new JPanel();
	
	MainFrame(){
		super(TITLE);
		Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
		
		monitorWidth = resolution.width;
		monitorHeight = resolution.height;
		
		this.setSize(monitorWidth * 9 / 10, monitorHeight * 9 / 10);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
	}
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.createFrame();
	}
	
	private void createFrame() {
		goBoard = new GoBoard();
		controlPanel = new ControlPanel();
		
		

		this.add(controlPanel);
		this.add(goBoard);
		this.setVisible(true);
	}

}

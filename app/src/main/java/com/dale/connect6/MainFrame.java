package com.dale.connect6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

import com.dale.connect6.data.MyData;
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

		controlPanel.getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBoard.setStart(true);
				goBoard.setBlockNumber(controlPanel.getNumberOfDisabledPoint());
			}
			
		});
		
		controlPanel.getReDoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(goBoard.isWhiteTurnFirst()) {
					if(goBoard.isWhiteTurnSecond()) {
						goBoard.setWhiteTurnSecond(false);
					}
					else {
						goBoard.setWhiteTurnFirst(false);
						goBoard.setBlackTurnFirst(true);
						goBoard.setBlackTurnSecond(true);
					}
				}
				else if(goBoard.isBlackTurnFirst()) {
					if(goBoard.isBlackTurnSecond()) {
						goBoard.setBlackTurnSecond(false);
					}
					else {
						goBoard.setWhiteTurnFirst(true);
						goBoard.setWhiteTurnSecond(true);
					}
				}
				
				MyData data = MyData.clickedPoint.get(MyData.clickedPoint.size()-1);
				goBoard.getUsedEllipse()[data.getX()][data.getY()] = null;
				goBoard.getGameMatrix()[data.getX()][data.getY()] = -1;
				MyData.clickedPoint.remove(MyData.clickedPoint.size()-1);
				goBoard.repaint();
			}
		});
		
		controlPanel.getRestartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyData.clickedPoint.clear();
				controlPanel.setNumberOfDisabledPoint(0);
				controlPanel.getNumberOfDisabledPointTextField().setText("");
				
				goBoard.setBlackTurnFirst(false);
				goBoard.setFirstClick(false);
				goBoard.setBlockCount(0);
				goBoard.setBlockFilled(false);
				goBoard.setBlackTurnSecond(false);
				goBoard.setBlackTurnFirst(false);
				goBoard.setWhiteTurnSecond(false);
				goBoard.setWhiteTurnFirst(false);
				goBoard.setCurrentColor(Color.RED);
				goBoard.setGameMatrix(new int[19][19]);
				goBoard.setUsedEllipse(new  Ellipse2D.Double[19][19]);
				goBoard.setEllipse(new Ellipse2D.Double[19][19]);
				goBoard.setStart(false);
				
				controlPanel.getStartButton().setEnabled(false);
				
				goBoard.repaint();
			}
		});
		this.add(controlPanel);
		this.add(goBoard);
		this.setVisible(true);
	}

}

package com.dale.connect6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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
	private JPanel infoPanel = new JPanel();
	private JLabel infoLabel = new JLabel("��");
	Font normalFont = new Font("",Font.BOLD, 80);
	Font winFont = new Font("",Font.BOLD, 50);
	
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
				
				for(int i=0; i<=18; i++) {
		        	for(int j=0; j<=18; j++) {
		        		goBoard.getGameMatrix()[i][j] = 0;
		        	}
				}
				
				infoPanel.setBackground(Color.BLACK);
				infoLabel.setForeground(Color.WHITE);
				infoLabel.setText("��");
				
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
				goBoard.setEnabled(true);
				goBoard.setWhiteWin(false);
				goBoard.setBlackWin(false);
				infoLabel.setFont(normalFont);
				controlPanel.getStartButton().setEnabled(false);
				controlPanel.getReDoButton().setEnabled(true);
				goBoard.repaint();
			}
		});
		
		infoPanel.setBackground(Color.BLACK);
		infoPanel.setBounds(700, 350, 250, 250);
		infoPanel.setLayout(null);
		infoLabel.setFont(normalFont);
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setBounds(85, 40, 165, 165);
		infoPanel.add(infoLabel);
		goBoard.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				if(goBoard.isWhiteWin()) {
					infoPanel.setBackground(Color.WHITE);
					infoLabel.setText("�� ��!");
					infoLabel.setFont(winFont);
					controlPanel.getReDoButton().setEnabled(false);
					infoLabel.setForeground(Color.BLACK);
					goBoard.setEnabled(false);
				}
				else if(goBoard.isBlackWin()) {
					infoPanel.setBackground(Color.BLACK);
					infoLabel.setText("�� ��!");
					infoLabel.setFont(winFont);
					controlPanel.getReDoButton().setEnabled(false);
					infoLabel.setForeground(Color.WHITE);
					goBoard.setEnabled(false);
				}
				else if(goBoard.isWhiteTurnFirst()) {
					infoPanel.setBackground(Color.WHITE);
					infoLabel.setText("��");
					infoLabel.setForeground(Color.BLACK);
				}
				else if(goBoard.isBlackTurnFirst()) {
					infoPanel.setBackground(Color.BLACK);
					infoLabel.setText("��");
					infoLabel.setForeground(Color.WHITE);
				}
			}
		});
		
		this.add(infoPanel);
		this.add(controlPanel);
		this.add(goBoard);
		this.setVisible(true);
	}
}

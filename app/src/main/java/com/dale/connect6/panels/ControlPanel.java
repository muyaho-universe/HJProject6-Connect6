package com.dale.connect6.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.dale.connect6.MainFrame;

public class ControlPanel extends JPanel {
	private JTextField numberOfDisabledPointTextField = new JTextField();
	private int numberOfDisabledPoint;
	private JButton startButton = new JButton("시작하기");
	
	Font normalFont = new Font("",Font.BOLD, 20);
	public ControlPanel() {
		setBounds(700, 5, 100, MainFrame.monitorHeight * 7 / 10);
		setBackground(Color.BLACK);
		setLayout(null);
		createPanel();
	}
	
	private void createPanel() {
		numberOfDisabledPointTextField.setBounds(15, 15, 100, 30);
		numberOfDisabledPointTextField.setFont(normalFont);
		numberOfDisabledPointTextField.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					numberOfDisabledPoint = Integer.parseInt(numberOfDisabledPointTextField.getText());
				}catch(Exception e1) {
					
				}
			}
		});
		
		startButton.setFont(normalFont);
		startButton.setBounds(15, 70, 100,30);
		
		this.add(startButton);
		this.add(numberOfDisabledPointTextField);
	}
}

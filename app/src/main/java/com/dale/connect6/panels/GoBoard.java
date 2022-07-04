package com.dale.connect6.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import com.dale.connect6.MainFrame;

public class GoBoard extends JPanel {
	public GoBoard(){
		setBounds(5, 5, 600, 600);
		setBackground(new Color(220, 179, 92));
	}
	
	@Override
	public void paintComponent(Graphics g){
	    super.paintComponent(g); // 부모 페인트호출
	    Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        for(int i=0; i<=540; i+=540/18) {
           g2d.drawLine(i+30, 30, i+30, 570);
           g2d.drawLine(30, i+30, 570, i+30);
        }
        for(int i = 120; i <= 480; i += 180) {
        	for(int j = 120; j <= 480; j += 180) {
        		g2d.drawOval(i-2, j-2, 4, 4);
        		g2d.fill(new Ellipse2D.Double(i-2, j-2, 4, 4));
        	}
        }
	}
	
	
}

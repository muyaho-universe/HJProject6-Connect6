package com.dale.connect6.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import com.dale.connect6.MainFrame;
import com.dale.connect6.data.MyData;

public class GoBoard extends JPanel {
	Ellipse2D.Double[][] ellipse = new Ellipse2D.Double[20][20];
	Ellipse2D.Double[][] usedEllipse = new Ellipse2D.Double[20][20];
	Color currentColor = Color.BLACK;
	
	private boolean firstClick = false;
	private boolean whiteTurnFirst = false;
	private boolean whiteTurnSecond = false;
	private boolean blackTurnFirst = false;
	private boolean blackTurnSecond = false;
	
	public GoBoard(){
		setBounds(5, 5, 600, 600);
		setBackground(new Color(220, 179, 92));
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				for(int i=0; i<=18; i++) {
		        	for(int j=0; j<=18; j++) {
		        		if(usedEllipse[i][j] == null || !usedEllipse[i][j].contains(e.getPoint())) {
		        			
		        			if(ellipse[i][j].contains(e.getPoint())) {
		        				usedEllipse[i][j] = new Ellipse2D.Double(i*30+20, j*30+20,20, 20);
			        			System.out.println(i+ " " + j);
			        			if(!firstClick) {
			        				MyData newData = new MyData(i, j, currentColor);
			        				currentColor = Color.WHITE;
			        				MyData.clickedPoint.add(newData);
			        				System.out.println("firstClick " + currentColor);
			        				whiteTurnFirst = true;
			        				firstClick =true;
			        			}
			        			
			        			else {
			        				if(whiteTurnFirst) {
			        					if(whiteTurnSecond) {
			        						System.out.println("whiteTurnSecond " + currentColor);
			        						MyData newData = new MyData(i, j, currentColor);
			        						MyData.clickedPoint.add(newData);
			        						currentColor = Color.BLACK;
			        						whiteTurnFirst = false;
			        						whiteTurnSecond=false;
			        						blackTurnFirst = true;
			        						
			        					}
			        					else {
			        						System.out.println("whiteTurnFirst " + currentColor);
			        						MyData newData = new MyData(i, j, currentColor);
			        						MyData.clickedPoint.add(newData);
			        						whiteTurnSecond = true;
			        					}
			        				}
			        				else {
			        					if(blackTurnSecond) {
			        						System.out.println("blackTurnSecond " + currentColor);
			        						MyData newData = new MyData(i, j, currentColor);
			        						MyData.clickedPoint.add(newData);
			        						currentColor = Color.WHITE;
			        						whiteTurnFirst = true;
			        						blackTurnFirst = false;
			        						blackTurnSecond = false;
			        					}
			        					else {
			        						System.out.println("blackTurnFirst " + currentColor);
			        						MyData newData = new MyData(i, j, currentColor);
			        						MyData.clickedPoint.add(newData);
			        						blackTurnSecond = true;
			        					}
			        				}
			        			}
			        			repaint();
			        		}
		        		}
		        		else {
		        			System.out.println("Already Taken");
		        		}
		        		
		        	}
		        }
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				for(int i=0; i<=18; i++) {
		        	for(int j=0; j<=18; j++) {
		        		if(ellipse[i][j].contains(e.getPoint())) {
		        			System.out.println(i+ " " + j);
		        		}
		        	}
				}
			
		        		
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g){
	    super.paintComponent(g); // 부모 페인트호출
	    Graphics2D g2d = (Graphics2D) g.create();
	    Graphics2D g2Point = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        for(int i=0; i<=18; i++) {
        	for(int j=0; j<=18; j++) {
        		ellipse[i][j] = new Ellipse2D.Double(i*30+20, j*30+20,20, 20);
        	}
            g2d.drawLine(i*30+30, 30, i*30+30, 570);
            g2d.drawLine(30, i*30+30, 570, i*30+30);
        }
        for(int i = 120; i <= 480; i += 180) {
        	for(int j = 120; j <= 480; j += 180) {
        		g2d.drawOval(i-2, j-2, 4, 4);
        		g2d.fillOval(i-3, j-3, 6, 6);
        	}
        }
        if(!MyData.clickedPoint.isEmpty()) {
        	for(MyData d: MyData.clickedPoint) {
        		g2Point.setColor(d.getColor());
        		g2Point.fillOval(d.getX()*30 +20, d.getY()*30 +20, 20, 20);
        	}
        	
        }
	}
	
	
}

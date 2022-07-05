package com.dale.connect6.panels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dale.connect6.MainFrame;
import com.dale.connect6.data.MyData;

public class GoBoard extends JPanel {
	Ellipse2D.Double[][] ellipse = new Ellipse2D.Double[20][20];
	Ellipse2D.Double[][] usedEllipse = new Ellipse2D.Double[20][20];
	
	private int[][] gameMatrix = new int[19][19];
	Color currentColor = Color.RED;
	
	private boolean firstClick = false;
	private boolean whiteTurnFirst = false;
	private boolean whiteTurnSecond = false;
	private boolean blackTurnFirst = false;
	private boolean blackTurnSecond = false;
	private boolean isBlockFilled = false;
	private boolean start = false;
	private boolean whiteWin = false;
	private boolean blackWin = false;
	
	private int blockCount = 0;
	private int blockNumber = 4;
	
	private int x = -1;
	private int y = -1;
	
	public GoBoard(){
		setBounds(5, 5, 600, 600);
		setBackground(new Color(220, 179, 92));
		for(int i=0; i<=18; i++) {
        	for(int j=0; j<=18; j++) {
        		gameMatrix[i][j] = 0;
        	}
		}
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(start) {
					for(int i=0; i<=18; i++) {
			        	for(int j=0; j<=18; j++) {
			        		if(isBlockFilled) {
			        			if(usedEllipse[i][j] == null || !usedEllipse[i][j].contains(e.getPoint())) {
				        			if(ellipse[i][j].contains(e.getPoint())) {
				        				usedEllipse[i][j] = new Ellipse2D.Double(i*30+20, j*30+20,20, 20);
				        				try {
				        					AudioInputStream audio = AudioSystem.getAudioInputStream(new File("./assets/MP_Button.wav"));
				        					Clip clip = AudioSystem.getClip();
				        					clip.stop();
				        					clip.open(audio);
				        					clip.start();
				        				
				        				} catch(Exception e1) {
				        					e1.printStackTrace();
				        				}
				        				
					        			System.out.println(i+ " " + j);
					        			if(!firstClick) {
					        				currentColor = Color.BLACK;
					        				MyData newData = new MyData(i, j, currentColor);
					        				MyData.clickedPoint.add(newData);
					        				System.out.println("firstClick " + currentColor);
					        				currentColor = Color.WHITE;
					        				whiteTurnFirst = true;
					        				firstClick =true;
					        				gameMatrix[i][j] = 1;
					        			}
					        			else {
					        				if(whiteTurnFirst) {
					        					x = i;
					        					y = j;
					        					gameMatrix[i][j] = 2;
					        					currentColor = Color.WHITE;
					        					if(whiteTurnSecond) {
					        						MyData newData = new MyData(i, j, currentColor);
					        						MyData.clickedPoint.add(newData);
					        						whiteTurnFirst=false;
					        						blackTurnFirst = true;
					        					}
					        					else {
					        						MyData newData = new MyData(i, j, currentColor);
					        						MyData.clickedPoint.add(newData);
					        						whiteTurnSecond = true;
					        						blackTurnSecond = false;
					        					}
					        				}
					        				else {
					        					x = i;
					        					y = j;
					        					currentColor = Color.BLACK;
					        					gameMatrix[i][j] = 1;
					        					if(blackTurnSecond) {
					        						MyData newData = new MyData(i, j, currentColor);
					        						MyData.clickedPoint.add(newData);
					        						
					        						whiteTurnFirst = true;
					        						blackTurnFirst = false;
					        					}
					        					else {
					        						MyData newData = new MyData(i, j, currentColor);
					        						
					        						MyData.clickedPoint.add(newData);
					        						blackTurnSecond = true;
					        						whiteTurnSecond = false;
					        					}
					        				}
					        			}
					        			repaint();
					        			int result = -1;
				        				try {
				        					result = checkFinishGo(x,y);
				        				}catch (Exception e1) {
				        				}
				        				System.out.println(result);
				        				if(result==1) {
				        					try {
					        					AudioInputStream audio = AudioSystem.getAudioInputStream(new File("./assets/MP_5 Sec_Crowd_Cheer.wav"));
					        					Clip clip = AudioSystem.getClip();
					        					clip.stop();
					        					clip.open(audio);
					        					clip.start();
					        				
					        				} catch(Exception e1) {
					        					e1.printStackTrace();
					        				}
				        					blackWin= true;
				        				}
				        				else if (result==2) {
				        					try {
					        					AudioInputStream audio = AudioSystem.getAudioInputStream(new File("./assets/MP_5 Sec_Crowd_Cheer.wav"));
					        					Clip clip = AudioSystem.getClip();
					        					clip.stop();
					        					clip.open(audio);
					        					clip.start();
					        				
					        				} catch(Exception e1) {
					        					e1.printStackTrace();
					        				}
				        					whiteWin= true;
				        				}
					        		}
				        		}
				        		else {
				        			System.out.println("Already Taken");
				        		}
			        		}
			        		else {
			        			if(blockCount == blockNumber) {
		        					isBlockFilled = true;
		        					currentColor = Color.BLACK;
		        					return;
		        				}
			        			if(usedEllipse[i][j] == null || !usedEllipse[i][j].contains(e.getPoint())) {
				        			if(ellipse[i][j].contains(e.getPoint())) {
				        				
				        				gameMatrix[i][j] = 3;
				        				
				        				usedEllipse[i][j] = new Ellipse2D.Double(i*30+20, j*30+20,20, 20);
					        			System.out.println(i+ " " + j);
				        				MyData newData = new MyData(i, j, currentColor);
				        				
				        				MyData.clickedPoint.add(newData);
				        				blockCount++;
				        				
				        				repaint();
				        			}
			        			}
			        		}
			        		
			        	}
			        }
				}
				else {
					System.out.println("Press start");
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(start) {
					for(int i=0; i<=18; i++) {
			        	for(int j=0; j<=18; j++) {
			        		if(isBlockFilled) {
			        			if(usedEllipse[i][j] == null || !usedEllipse[i][j].contains(e.getPoint())) {
				        			if(ellipse[i][j].contains(e.getPoint())) {
				        				if(whiteTurnFirst) {
				        					x = i;
				        					y = j;
				        				}
				        				if(whiteTurnFirst){
				        					x = i;
				        					y = j;
				        				}
				        			}
			        			}
			        		}
			        	}
					}
				}       			
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g){
	    super.paintComponent(g);
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
	
	public int checkFinishGo(int x, int y) {

		if(checkHorizontal(x, y, gameMatrix[x][y]) >= 6) {
			return gameMatrix[x][y];
		}
		else if(checkVertical(x, y, gameMatrix[x][y]) >= 6) {
			return gameMatrix[x][y];
		}
		else if(checkLRDiagonal(x, y, gameMatrix[x][y]) >= 6) {
			return gameMatrix[x][y];
		}
		else if(checkRLDiagonal(x, y, gameMatrix[x][y]) >= 6) {
			return gameMatrix[x][y];
		}

		return 0;
	}

	private int checkHorizontal(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return checkLeft(row - 1, col, rock) + 1 + checkRight(row + 1, col, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLeft(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkLeft(row - 1, col, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkRight(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkRight(row + 1, col, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkVertical(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return checkUp(row, col - 1, rock) + 1 + checkDown(row, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkUp(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkUp(row, col - 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkDown(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkDown(row, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonal(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return checkLRDiagonalUp(row - 1, col - 1, rock) + 1 + checkLRDiagonalDown(row + 1, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonalUp(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkLRDiagonalUp(row - 1, col - 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonalDown(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkLRDiagonalDown(row + 1, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkRLDiagonal(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return checkRLDiagonalUp(row + 1, col - 1, rock) + 1 + checkRLDiagonalDown(row - 1, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkRLDiagonalUp(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkRLDiagonalUp(row + 1, col - 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkRLDiagonalDown(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkRLDiagonalDown(row - 1, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public Ellipse2D.Double[][] getEllipse() {
		return ellipse;
	}

	public void setEllipse(Ellipse2D.Double[][] ellipse) {
		this.ellipse = ellipse;
	}

	public Ellipse2D.Double[][] getUsedEllipse() {
		return usedEllipse;
	}

	public void setUsedEllipse(Ellipse2D.Double[][] usedEllipse) {
		this.usedEllipse = usedEllipse;
	}

	public int[][] getGameMatrix() {
		return gameMatrix;
	}

	public void setGameMatrix(int[][] gameMatrix) {
		this.gameMatrix = gameMatrix;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public boolean isFirstClick() {
		return firstClick;
	}

	public void setFirstClick(boolean firstClick) {
		this.firstClick = firstClick;
	}

	public boolean isWhiteTurnFirst() {
		return whiteTurnFirst;
	}

	public void setWhiteTurnFirst(boolean whiteTurnFirst) {
		this.whiteTurnFirst = whiteTurnFirst;
	}

	public boolean isWhiteTurnSecond() {
		return whiteTurnSecond;
	}

	public void setWhiteTurnSecond(boolean whiteTurnSecond) {
		this.whiteTurnSecond = whiteTurnSecond;
	}

	public boolean isBlackTurnFirst() {
		return blackTurnFirst;
	}

	public void setBlackTurnFirst(boolean blackTurnFirst) {
		this.blackTurnFirst = blackTurnFirst;
	}

	public boolean isBlackTurnSecond() {
		return blackTurnSecond;
	}

	public void setBlackTurnSecond(boolean blackTurnSecond) {
		this.blackTurnSecond = blackTurnSecond;
	}

	public boolean isBlockFilled() {
		return isBlockFilled;
	}

	public void setBlockFilled(boolean isBlockFilled) {
		this.isBlockFilled = isBlockFilled;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isWhiteWin() {
		return whiteWin;
	}

	public void setWhiteWin(boolean whiteWin) {
		this.whiteWin = whiteWin;
	}

	public boolean isBlackWin() {
		return blackWin;
	}

	public void setBlackWin(boolean blackWin) {
		this.blackWin = blackWin;
	}
	
	
}

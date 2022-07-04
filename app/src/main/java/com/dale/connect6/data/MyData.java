package com.dale.connect6.data;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.*;

public class MyData {
	private int x;
	private int y;
	private Color color;
	
	public static ArrayList<MyData> clickedPoint = new ArrayList<MyData>();
	public static ArrayList<Ellipse2D> usedEllipse2D = new ArrayList<Ellipse2D>();
	
	public MyData(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		Ellipse2D used = new Ellipse2D.Double(x*30+20, y*30+20,20, 20);
		usedEllipse2D.add(used);
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}

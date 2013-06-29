package com.theoc.pixhell.utilities;

import android.graphics.Point;

public class PointUtil {
	
	public static Point ZERO = new Point(0, 0);
	
	public static Point normalize(Point p) {
		if (p == null) {
			throw new IllegalArgumentException("Point was null when attempting normalize");
		}
		double magnitude = PointUtil.magnitude(p);
		return new Point((int) (p.x / magnitude), (int) (p.y / magnitude));
	}
	
	public static Point minus(Point one, Point two) {
		if (one == null || two == null) {
			throw new IllegalArgumentException("Point was null when attempting normalize");
		}
		return new Point(one.x - two.x, one.y - two.y);
	}
	
	public static double magnitude(Point p) {
		if (p == null) {
			throw new IllegalArgumentException("Point was null when attempting normalize");
		}
		return Math.sqrt(p.x * p.x + p.y * p.y);
	}
}

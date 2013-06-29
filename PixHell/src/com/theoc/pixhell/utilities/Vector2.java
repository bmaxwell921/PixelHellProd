package com.theoc.pixhell.utilities;

public class Vector2 {
	
	public final static Vector2 ZERO = new Vector2(0, 0);
	
	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public void multiply(float scalar) {
		Vector2 result = Vector2.multiply(this, scalar);
		this.x = result.x;
		this.y = result.y;
	}
	
	public void componentwiseMult(Vector2 other) {
		Vector2 result = Vector2.componentwiseMult(this, other);
		this.x = result.x;
		this.y = result.y;
	}
	
	public static Vector2 componentwiseMult(Vector2 one, Vector2 two) {
		if (one == null || two == null) {
			throw new IllegalArgumentException("A vector was null");
		}
		return new Vector2(one.x * two.x, one.y * two.y);
	}
	
	public static Vector2 multiply(Vector2 vect, float scalar) {
		if (vect == null) {
			throw new IllegalArgumentException("Vect was null");
		}
		return new Vector2(vect.x * scalar, vect.y * scalar);
	}
	
	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}
	
	public void normalize() {
		Vector2 result = Vector2.normalize(this);
		this.x = result.x;
		this.y = result.y;
	}
	
	public static Vector2 normalize(Vector2 vect) {
		if (vect == null) {
			throw new IllegalArgumentException("Vect was null");
		}
		float magnitude = (float) vect.magnitude();
		if (magnitude == 0) {
			return ZERO;
		}
		
		return new Vector2(vect.x / magnitude, vect.y / magnitude);
	}
	
	public void add(Vector2 other) {
		Vector2 result = Vector2.add(this, other);
		this.x = result.x;
		this.y = result.y;
	}
	
	public static Vector2 add(Vector2 first, Vector2 second) {
		if (first == null || second == null) {
			throw new IllegalArgumentException("First or second was null");
		}
		return new Vector2(first.x + second.x, first.y + second.y);
	}
	
	public void subtract(Vector2 other) {
		Vector2 result = Vector2.subtract(this, other);
		this.x = result.x;
		this.y = result.y;
	}
	
	public static Vector2 subtract(Vector2 first, Vector2 last) {
		if (first == null || last == null) {
			throw new IllegalArgumentException("First or second vector was null");
		}
		return new Vector2(first.x - last.x, first.y - last.y);
	}
	
	public double distance(Vector2 other) {
		if (other == null) {
			throw new IllegalArgumentException("Vector was null");
		}
		return Vector2.distance(this, other);
	}
	
	public static double distance(Vector2 first, Vector2 second) {
		if (first == null || second == null) {
			throw new IllegalArgumentException("Vector was null");
		}
		return new Vector2(Vector2.subtract(first, second)).magnitude();
	}
}

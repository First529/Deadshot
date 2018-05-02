package swing;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.LinkedList;

public abstract class GameObject {
	
	public double x, y;
	public ObjectId id;
	public double velocityX = 0, velocityY = 0;
	public boolean falling = true;
	public boolean jumping = false;
	public int facing = 0;
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public GameObject(double x, double y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void update(LinkedList<GameObject> object);
	
	public abstract void render(Graphics g) ;
	
	public abstract Shape getBounds();
	
	//Position
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	
	//Velocity
	
	public double getVelocityX() {
		return velocityX;
	}
	
	public double getVelocityY() {
		return velocityY;
	}
	
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	
	public ObjectId getId() {
		return id;
	}
	
	public void setFacing(int facing) {
		this.facing = facing;
	}
	
	public int getFacing() {
		return facing;
	}
	

}

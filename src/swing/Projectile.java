package swing;

public class Projectile {
	
	private static final double G = 9.81;
	
	private double angle;
	
	private double initVelocity;
	
	private double time;
	
	public Projectile(double angle, double velocity) {
		this.angle = Math.toRadians(angle);
		this.initVelocity = velocity;
		this.time = 0.0;
	}
	
	public double getTotalTime() {
		return (2.0 * initVelocity * Math.sin(angle) )/ G;
	}
	
	
	
	public double[] getPosX() {
		double[] arrayX = new double[20];
		int i = 1;
		while (i <= 20) {
			time += timePos();
			arrayX[i] = getVelocityX()* time;
			i++;
		}
		return arrayX;
	}
	
	public double[] getPosY() {
		double[] arrayY = new double[20];
		int i = 1;
		while (i <= 20) {
			time += timePos();
			arrayY[i] = (getVelocityY()*time) + 0.5*-9.81*time*time;
			i++;
		}
		return arrayY;
	}
	
	public double timePos() {
		return getTotalTime() / 20;
	}
	
	public double getXIncrement() {
		return getVelocityX() * timePos();
	}
	
	public double getMaximumHeight() {
		return (0.5*initVelocity*initVelocity*Math.sin(angle)*Math.sin(angle))/G;
	}
	
	public double getVelocityX() {
		return initVelocity * Math.cos(angle);
	}
	
	public double getVelocityY() {
		return initVelocity * Math.sin(angle);
	}

}
	


	
	





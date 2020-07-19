/** Class Body 
 *  and 2 Body constructors.
 */
public class Body {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	static double Gra = 6.67e-11; 

	/** The first Body constructor. */
	public Body (double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** The second Body constructor. */
	public Body (Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	/** The first helper method calculating distance between two Bodies. */
	public double calcDistance (Body b) {
		return Math.sqrt((this.xxPos - b.xxPos)*(this.xxPos - b.xxPos) + (this.yyPos - b.yyPos)*(this.yyPos - b.yyPos));
	}

	/** The second helper method calculating gravitational force between two Bodies. */
	public double calcForceExertedBy (Body b) {
		double dis = this.calcDistance(b);
		return Gra * this.mass * b.mass / dis / dis;
	}

	/** The third helper method calculating gravitational force in x-direction between two Bodies. */
	public double calcForceExertedByX (Body b) {
		double force = this.calcForceExertedBy(b);
		return force / this.calcDistance(b) * (b.xxPos - this.xxPos);
	}

	/** The fourth helper method calculating gravitational force in y-direction between two Bodies. */
	public double calcForceExertedByY (Body b) {
		double force = this.calcForceExertedBy(b);
		return force / this.calcDistance(b) * (b.yyPos - this.yyPos);
	}

	/** The fifth helper method calculating net gravatational force in x-direction 
	 * between a Body and a group of Bodies. */
	 public double calcNetForceExertedByX (Body[] all) {
	 	 double sumFx = 0;
		 for (int i = 0; i < all.length; i += 1) {
		 	 if (this.xxPos == all[i].xxPos && this.yyPos == all[i].yyPos) {
			 	 continue;
			 }
			 sumFx += this.calcForceExertedByX(all[i]);
		 }
		 return sumFx;
	 }

	/** The sixth helper method calculating net gravatational force in y-direction 
	 * between a Body and a group of Bodies. */
	 public double calcNetForceExertedByY (Body[] all) {
	 	 double sumFy = 0;
		 for (int i = 0; i < all.length; i += 1) {
		 	 if (this.xxPos == all[i].xxPos && this.yyPos == all[i].yyPos) {
			 	 continue;
			 }
			 sumFy += this.calcForceExertedByY(all[i]);
		 }
		 return sumFy;
	 }

	 /** The seventh helper function updating a Body's velocity and position. */
	 public void update (double dt, double fX, double fY) {
	 	 double aX = fX / this.mass;
		 double aY = fY / this.mass;
		 this.xxVel += aX * dt;
		 this.yyVel += aY * dt;
		 this.xxPos += this.xxVel * dt;
		 this.yyPos += this.yyVel * dt;
	 }

	 /** The last method, drawing the Body's image at its position. */
	 public void draw () {
	 	 StdDraw.picture (this.xxPos, this.yyPos, "images/" + this.imgFileName);
	 }
}
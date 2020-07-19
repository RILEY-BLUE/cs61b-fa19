/** NBody is a class that actually runs the simulation,
 * and has no constructors. */
 public class NBody {
     /** This readRadius function returns the universe's radius in the simulation. */
     public static double readRadius (String fileName) {
      In in = new In (fileName);
      int NumberOfPlanets = in.readInt(); 
      double RadiusOfUniverse = in.readDouble();
      return RadiusOfUniverse;
	 }

     /** This readBodies function returns an array of Bodies corresponding to the 
      * Bodies in the given file. */
      public static Body[] readBodies (String fileName) {
       In in = new In (fileName);
       int NumberOfPlanets = in.readInt();
       double RadiusOfUniverse = in.readDouble();
       Body[] b = new Body[NumberOfPlanets]; // the first new command
       /** This is actually the most difficult part of proj0. When giving value to 
        * a new array, you need to use the new command one more time to create the 
        * concrete array. Otherwise it will only be a null pointer. */
       for (int i = 0; i < NumberOfPlanets; i += 1) {
        b[i] = new Body (in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString()); 
	   }
       return b;
	  }

      /** This main method draws the initial universe. */
      public static void main (String[] args) {
       /** Collecting all needed input. */
       System.out.println("Please supply T as the 0th command line argument.");
       double T = Double.parseDouble (args[0]);
       System.out.println("Please supply dt as the 1th command line argument.");
       double dt = Double.parseDouble (args[1]);
       System.out.println("Please supply filename as the 2nd command line argument.");
       String filename = args[2];
       double UniR = NBody.readRadius(filename);
       Body[] Planets = NBody.readBodies(filename);

       /** Drawing the background. */
       StdDraw.enableDoubleBuffering();
       StdDraw.clear();
       StdDraw.setScale (-UniR, UniR);
       StdDraw.picture(0, 0, "images/starfield.jpg");

       /** Drawing more than one body. */
       for (int i = 0; i < Planets.length; i += 1) {
        Planets[i].draw();
	   }
       /** Only when you call show() does your drawing get copied from the
		* offscreen canvas to the onscreen canvas, where it is displayed
		* in the standard drawing window. */
       StdDraw.show();

       /** Adding audio. */
       StdAudio.play ("./audio/2001.mid");
       
       /** Creating an animation. */
       for (int t = 0; t <= T; t += dt) {
        double[] xForces = new double[Planets.length];
        double[] yForces = new double[Planets.length];
        for (int i = 0; i < Planets.length; i += 1) {
        xForces[i] = Planets[i].calcNetForceExertedByX (Planets);
        yForces[i] = Planets[i].calcNetForceExertedByY (Planets);
		}
        for (int i = 0; i < Planets.length; i += 1) {
         Planets[i].update (dt, xForces[i], yForces[i]);
		}
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < Planets.length; i += 1) {
         Planets[i].draw();
	    }
        StdDraw.show();
        StdDraw.pause(10);
	   }

       /** Printing the universe. */
       StdOut.printf("%d\n", Planets.length);
       StdOut.printf("%.2e\n", UniR);
       for (int i = 0; i < Planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
        Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
       }
	  }
 }
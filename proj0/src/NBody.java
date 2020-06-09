import examples.StdDraw;

/** This program mimics the universe by applying Newton's law of universal gravitation
 * @author Thang Cao
 * @date 06/01/2020
 * */
public class NBody {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Invalid input!");
            return;
        }

        int time;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);


        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);

        for (time = 0; time <= T; time += dt) {
            Double[] xForces = new Double[planets.length];
            Double[] yForces = new Double[planets.length];

            for (int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet p : planets){
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(5);
        }
    }


    public static double readRadius(String textName){
        In in = new In(textName);
        int numOfPlanets = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static Planet[] readPlanets(String textName){
        In in = new In(textName);

        int numOfPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numOfPlanets];

        for (int i = 0; i < numOfPlanets; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
            return planets;
    }
}

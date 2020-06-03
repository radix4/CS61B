public class TestPlanet {
    public static void main(String[] args) {
        printPairwiseForce();
    }

    public static void printPairwiseForce(){
        Planet p1 = new Planet(1,1,3,4,5, "Bla");
        Planet p2 = new Planet(5,5,3,3,5,"Also bla");

        Planet[] planets = {p1, p2};

        System.out.println("Check gravitational force:");
        System.out.println(p1.gravitationalForce(p2));
        System.out.println(p2.gravitationalForce(p1));
        System.out.println();


        System.out.println("Check distance:");
        System.out.println(p1.calcDistance(p2));
        System.out.println(p2.calcDistance(p1));
        System.out.println();


        System.out.println("Check force components:");
        System.out.println(p1.calcNetForceExertedByX(planets));
        System.out.println(p2.calcNetForceExertedByX(planets));

        System.out.println(p1.calcNetForceExertedByY(planets));
        System.out.println(p2.calcNetForceExertedByY(planets));
    }
}

public class PointTest {
    public static void main(String[] args) {
        System.out.println("Point objects generated = " + Point.getCount());

        Point a = new Point(-2, 3);
        Point b = new Point(4, -7);

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        double d = a.distanceTo(b);
        System.out.println("Distance between a and b = " + Double.toString(d));

        Point c = new Point(10, a.getY());
        System.out.println("c = " + c);

        System.out.println("Point objects generated = " + Point.getCount());
    }
}

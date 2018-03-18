package main.utils;

import main.shape.Point;

public class VectorUtils {
    public static double[] diff(double[] a, double[] b) {
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    public static double sum(double[] a) {
        double res = 0;
        for (double anA : a) {
            res += anA;
        }
        return res;
    }

    public static double[] sum(double[] a, double[] b) {
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public static double[] multiply(double[] a, double[] b) {
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] * b[i];
        }
        return c;
    }

    public static double scalar(double[] a, double[] b) {
        return sum(multiply(a, b));
    }

    public static double[] multiply(double[] a, double b) {
        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] * b;
        }
        return c;
    }

/*   public  static float multiple(Point A, Point B) {
        return (A.getX() * B.getX()) + (A.getY() * B.getY()) + (A.getZ() * B.getZ());
    }*/
}

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


    public static float scalar(Point A, Point B) {
        return (float)((A.getX() * B.getX()) + (A.getY() * B.getY()) + (A.getZ() * B.getZ()));
    }

    public static Point multiply(Point A, float b) {
        return new Point(A.getX() * b, A.getY() * b, A.getZ() * b);
    }
    public static Point multiply(Point A, double b) {
        return new Point(A.getX() * b, A.getY() * b, A.getZ() * b);
    }

    public static Point diff(Point A, Point B) {
        return new Point(A.getX() - B.getX(), A.getY() - B.getY(), A.getZ() - B.getZ());
    }

    public static Point sum(Point A, Point B) {
        return new Point(A.getX() + B.getX(), A.getY() + B.getY(), A.getZ() + B.getZ());
    }




    public static IntPoint multiply(IntPoint A, float b) {
        return new IntPoint((int)(A.getX() * b), (int)(A.getY() * b), (int)(A.getZ() * b));
    }


    public static IntPoint multiply(IntPoint A, double b) {
        return new IntPoint((int)(A.getX() * b), (int)(A.getY() * b), (int)(A.getZ() * b));
    }

    public static IntPoint diff(IntPoint A, IntPoint B) {
        return new IntPoint(A.getX() - B.getX(), A.getY() - B.getY(), A.getZ() - B.getZ());
    }

    public static IntPoint sum(IntPoint A, IntPoint B) {
        return new IntPoint(A.getX() + B.getX(), A.getY() + B.getY(), A.getZ() + B.getZ());
    }

    public static Pixel multiply(Pixel A, float b) {
        return new Pixel((int)(A.getX() * b), (int)(A.getY() * b));
    }


    public static Pixel multiply(Pixel A, double b) {
        return new Pixel((int)(A.getX() * b), (int)(A.getY() * b));
    }

    public static Pixel diff(Pixel A, Pixel B) {
        return new Pixel(A.getX() - B.getX(), A.getY() - B.getY());
    }

    public static Pixel sum(Pixel A, Pixel B) {
        return new Pixel(A.getX() + B.getX(), A.getY() + B.getY());
    }

}

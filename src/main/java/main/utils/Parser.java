package main.utils;

import main.shape.Point;
import main.shape.Triangle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static List<Point> sVertices;
    private static List<Triangle> sTriangle;

    public static void readFile(String PATH, int size) {

        if (sVertices == null)
            sVertices = new ArrayList<Point>();
        else
            sVertices.clear();
        if (sTriangle == null)
            sTriangle = new ArrayList<Triangle>();
        else
            sTriangle.clear();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH), Charset.forName("UTF-8")));
            String line;
            while ((line = reader.readLine()) != null && !line.equals("")) {
                String[] array = line.split(" ");
                //if (array[0].equals("vt")
                //System.out.println("vt");
                //else if (array[0].equals("vn"))
                //System.out.println("vn");
                if (array[0].equals("f"))
                    addTriangle(array);
                else if (array[0].equals("v"))
                    addPoint(array, size);

            }
            System.out.println(sVertices.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void addPoint(String[] array, int size) {
        try {
            if (sVertices == null)
                sVertices = new ArrayList<Point>();
            int s = size / 2;
            int x = (int) (Double.parseDouble(array[1]) * s) + s;
            int y = (int) (Double.parseDouble(array[2]) * s) + s;
            int z = (int) (Double.parseDouble(array[3]) * s) + s;

            System.out.println("Точка: x = " + x + ", y = " + y + ", z = " + z);

            sVertices.add(new Point(x, y, z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTriangle(String[] array) {
        if (sVertices == null || sVertices.size() == 0)
            return;
        int v1 = Integer.parseInt(array[1].split("/")[0]);
        int v2 = Integer.parseInt(array[2].split("/")[0]);
        int v3 = Integer.parseInt(array[3].split("/")[0]);

        sTriangle.add(new Triangle(sVertices.get(v1 - 1), sVertices.get(v2 - 1), sVertices.get(v3 - 1)));
    }

    public static List<Triangle> getTriangle() {
        return sTriangle;
    }

}

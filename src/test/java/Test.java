import main.shape.Point;
import main.utils.Parser;
import main.utils.TGAReader;
import main.utils.VectorUtils;

import static junit.framework.TestCase.assertEquals;


public class Test {
    @org.junit.Test
    public void multiplication(){
        assertEquals(6.0,VectorUtils.sum(new double[]{1,2,3}));
    }

    @org.junit.Test
    public void sum() {
        assertEquals(6.0,VectorUtils.sum(new double[]{1,2,3}));
        assertEquals(12.0,VectorUtils.sum(new double[]{7,2,3}));
        assertEquals(0.0,VectorUtils.sum(new double[]{0,0,0}));
        assertEquals(3.0,VectorUtils.sum(new double[]{1,1,1}));
        assertEquals(100.0,VectorUtils.sum(new double[]{100,0,0}));
        assertEquals(3.0,VectorUtils.sum(new double[]{1,-1,3}));
        assertEquals(6.0,VectorUtils.sum(new double[]{1,2,3}));
    }

    @org.junit.Test
    public void scalar() {
        assertEquals(3.0f, VectorUtils.scalar(new Point(1, 1, 1), new Point(1, 1, 1)));
        assertEquals(0.0f, VectorUtils.scalar(new Point(0, -1, 0), new Point(1, 0, -1)));
        assertEquals(-1.0f, VectorUtils.scalar(new Point(1, 1, 1), new Point(0, -1, 0)));
        assertEquals(30.0f, VectorUtils.scalar(new Point(10, 10, 1), new Point(1, 1, 10)));
        assertEquals(34.0f, VectorUtils.scalar(new Point(2, 4, 2), new Point(9, 2, 4)));
        assertEquals(1.0f, VectorUtils.scalar(new Point(1, 0, 1), new Point(1, 1, 0)));
    }

    @org.junit.Test
    public void parse() {
        Parser.readFile("C:\\JavaProjects\\JavaGraphics\\JavaGraphics\\src\\main\\resources\\african_head.obj");
        Parser.readFile("C:\\JavaProjects\\JavaGraphics\\JavaGraphics\\src\\main\\resources\\african_head.ob");
        Parser.readFile("C:\\JavaProjects\\JavaGraphics\\JavaGraphics\\src\\main\\resources\\african_head_diffuse .tga");
        Parser.readFile("");
        Parser.readFile("123");
        //
    }

    @org.junit.Test
    public void parseTGA() {
        assertEquals(null, TGAReader.getImage("C:\\JavaProjects\\JavaGraphics\\JavaGraphics\\src\\main\\resources\\"));
        assertEquals(null, TGAReader.getImage("C:\\JavaProjects\\JavaGraphics\\JavaGraphics\\src\\main\\resources\\african_head.oj"));
        TGAReader.getImage("C:\\JavaProjects\\JavaGraphics\\JavaGraphics\\src\\main\\resources\\african_head_diffuse .tga");
        assertEquals(null, TGAReader.getImage(""));
        assertEquals(null, TGAReader.getImage("123"));
    }
}

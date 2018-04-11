import main.helpers.TGAReader;
import main.rendering.BackFaceCulling;
import main.rendering.Luminosity;
import main.rendering.ZBuffer;
import main.shape.Point;
import main.helpers.Parser;
import main.shape.primtives.Face;
import main.utils.VectorUtils;

import java.awt.image.BufferedImage;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static main.ui.MainFrame.generateImage;


public class Test {
    @org.junit.Test
    public void multiplication() {
        assertEquals(6.0, VectorUtils.sum(new double[]{1, 2, 3}));
    }

    @org.junit.Test
    public void sum() {
        assertEquals(6.0, VectorUtils.sum(new double[]{1, 2, 3}));
        assertEquals(12.0, VectorUtils.sum(new double[]{7, 2, 3}));
        assertEquals(0.0, VectorUtils.sum(new double[]{0, 0, 0}));
        assertEquals(3.0, VectorUtils.sum(new double[]{1, 1, 1}));
        assertEquals(100.0, VectorUtils.sum(new double[]{100, 0, 0}));
        assertEquals(3.0, VectorUtils.sum(new double[]{1, -1, 3}));
        assertEquals(6.0, VectorUtils.sum(new double[]{1, 2, 3}));
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
        Parser.readFile("src/main/resources/african_head.obj");
        testZBuffer(Parser.getFaces());
        testBackfaceCulling(Parser.getFaces());
        Parser.readFile("src/main/resources/african_head.ob");
        Parser.readFile("src/main/resources/african_head_diffuse.tga");
        Parser.readFile("");
        Parser.readFile("123");
        //
    }

    public void testZBuffer(List<Face> faces) {
        double[] zBuffer = new double[300];
        BufferedImage image = generateImage(600, 200);
        Luminosity.Companion.render(faces, new ZBuffer(image, generateDiffuseImage(), false, zBuffer), true);
        image = generateImage(100, 0);
        Luminosity.Companion.render(faces, new ZBuffer(image, generateDiffuseImage(), false, zBuffer), true);
        image = generateImage(0, 200);
        Luminosity.Companion.render(faces, new ZBuffer(image, generateDiffuseImage(), false, zBuffer), true);
    }

    public void testBackfaceCulling(List<Face> faces) {
        BufferedImage image = generateImage(600, 200);
        Luminosity.Companion.render(faces, new BackFaceCulling(image), true);
        image = generateImage(100, 0);
        Luminosity.Companion.render(faces, new BackFaceCulling(image), true);
        image = generateImage(0, 200);
        Luminosity.Companion.render(faces, new BackFaceCulling(image), false);
    }


    private BufferedImage generateDiffuseImage() {
        return ((BufferedImage) TGAReader.getImage("src/main/resources/african_head_diffuse.tga", 200, 400));
    }

    @org.junit.Test
    public void parseTGA() {
        assertNull(TGAReader.getImage("src/main/resources/", 100, 200));
        assertNull(TGAReader.getImage("src/main/resources/african_head.oj", 100, 200));
        assertNotNull(TGAReader.getImage("src/main/resources/african_head_diffuse.tga", 100, 200));
        assertNull(TGAReader.getImage("", 100, 200));
        assertNull(TGAReader.getImage("123", 100, 200));
    }
}

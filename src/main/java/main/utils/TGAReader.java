package main.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TGAReader
{
    public static Image getImage(String fileName) throws IOException
    {
        File f = new File(fileName);
        byte[] buf = new byte[(int)f.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
        bis.read(buf);
        bis.close();
        return decode(buf);
    }

    private static int offset;

    private static int btoi(byte b)
    {
        int a = b;
        return (a<0?256+a:a);
    }

    private static int read(byte[] buf)
    {
        return btoi(buf[offset++]);
    }

    public static Image decode(byte[] buf) throws IOException
    {
        offset = 0;

        // Reading header
        for (int i=0;i<12;i++)
            read(buf);
        int width = read(buf)+(read(buf)<<8);
        int height = read(buf)+(read(buf)<<8);
        read(buf);
        read(buf);

        // Reading data
        int n = width*height;
        int[] pixels = new int[n];
        int idx=0;

        while (n>0)
        {
            int nb = read(buf);
            if ((nb&0x80)==0)
            {
                for (int i=0;i<=nb;i++)
                {
                    int b = read(buf);
                    int g = read(buf);
                    int r = read(buf);
                    pixels[idx++] = 0xff000000 | (r<<16) | (g<<8) | b;
                }
            }
            else
            {
                nb &= 0x7f;
                int b = read(buf);
                int g = read(buf);
                int r = read(buf);
                int v = 0xff000000 | (r<<16) | (g<<8) | b;
                for (int i=0;i<=nb;i++)
                    pixels[idx++] = v;
            }
            n-=nb+1;
        }

        BufferedImage bimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        bimg.setRGB(0,0,width,height,pixels,0,width);
        flip(bimg);
        return bimg;
    }

    static private void flip(BufferedImage image)
    {
        for (int i=0;i<image.getWidth();i++)
            for (int j=0;j<image.getHeight()/2;j++)
            {
                int tmp = image.getRGB(i, j);
                image.setRGB(i, j, image.getRGB(i, image.getHeight()-j-1));
                image.setRGB(i, image.getHeight()-j-1, tmp);
            }
    }
}
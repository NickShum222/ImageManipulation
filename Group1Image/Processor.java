/**
 * Starter code for Processor - the class that processes images.
 * <p> This class manipulated Java BufferedImages, which are effectively 2d arrays
 * of pixels. Each pixel is a single integer packed with 4 values inside it.</p>
 * 
 * <p>All methods added to this class should be static. In other words, you do not
 *    have to instantiate (create an object of) this class to use it - simply call
 *    the methods with Processor.methodName and pass a GreenfootImage to be manipulated.
 *    Note that you do not have to return the processed image, as you will be passing a
 *    reference to your image, and it will be altered by the method, as seen in the Blueify
 *    example.</p>
 *    
 * <p>Some methods, especially methods that change the size of the image (such as rotation
 *    or scaling) may require a GreenfootImage return type. This is because while it is
 *    possible to alter an image passed as a parameter, it is not possible to re-instantiate it, 
 *    which is required to change the size of a GreenfootImage</p>
 * 
 * <p>
 * I have included two useful methods for dealing with bit-shift operators so
 * you don't have to. These methods are unpackPixel() and packagePixel() and do
 * exactly what they say - extract red, green, blue and alpha values out of an
 * int, and put the same four integers back into a special packed integer. </p>
 * 
 * @author Jordan Cohen 
 * @version November 2013
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import greenfoot.*;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;

public class Processor  
{
    /**
     * Example colour altering method by Mr. Cohen. This method will
     * increase the blue value while reducing the red and green values.
     * 
     * Demonstrates use of packagePixel() and unpackPixel() methods.
     * 
     * @param bi    The BufferedImage (passed by reference) to change.
     */

    public static void brownify (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                //makes the picture more brown by balancing out the rgb values
                if(red < 150){
                    red+=2;
                } else if(red > 170){
                    red-=2;
                }
                if(green > 100){
                    green-=2;
                } else if(green < 80){
                    green+=2;
                }
                if(blue > 40){
                    blue-=2;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }

    }

    public static void coolFilter(BufferedImage bi){
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgba);
                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];  
                //make the picture cooler
                if (blue <254)
                    blue+=2;
                if (red >= 50)
                    red--;
                if (green <254)
                    green+=2;
         
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    
    
    public static void yellowify (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);

                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                //make picture more yellow
                if (blue >= 50)
                    blue--;
                if (red < 253)
                    red += 2;
                if (green < 253)
                    green += 2;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static void pinkify (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);

                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if(red < 245){
                    red++;
                }
                if(green < 130){
                    green++;
                }
                if(blue < 230){
                    blue++;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static void redify (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);

                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                //make picture more red
                if (blue >= 50)
                    blue --;
                if (red < 253)
                    red += 2;
                if (green >= 50)
                    green --;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static void negative (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);

                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                //Use negative filter algorithm to set the colors to negative
                blue = 255 - blue;
                red = 255 - red;
                green = 255 - green;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }

    public static void sepia (BufferedImage bi){
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                //Use sepia algorithm to set the colors to sepia filter
                int redSepia = (int)(red*0.393 + green*0.769 + blue*0.189);
                int blueSepia = (int)(red*0.272 + green*0.534 + blue*0.131);
                int greenSepia = (int)(red*0.393 + green*0.769 + blue*0.189);

                if(redSepia > 255){
                    red = 255;
                }else{
                    red = redSepia;
                }

                if(blueSepia > 255){
                    blue = 255;
                }else{
                    blue = blueSepia;
                }

                if(greenSepia > 255){
                    green = 255;
                }else{
                    green = greenSepia;
                }

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }

    }

    public static void greenify (BufferedImage bi)
    {
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);

                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                //make picture more green
                if (blue >= 50)
                    blue--;
                if (red >= 50)
                    red--;
                if (green < 253)
                    green += 2;
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    public static void transparency(BufferedImage bi){
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {

                int rgba = bi.getRGB(x, y);

                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];
                
                if (alpha>25){
                    alpha = alpha-3;
                }
                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }
    }
    
    public static void darken (BufferedImage bi){
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                int rgba = bi.getRGB(x, y);
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0];
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                if (blue >= 40)
                    blue-=3;
                if (red >= 40)
                    red-=3;
                if (green >= 40)
                    green-= 3;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }

    }

    public static void blueify (BufferedImage bi)
    {
        // Get image size to use in for loops
        int xSize = bi.getWidth();
        int ySize = bi.getHeight();

        // Using array size as limit
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Calls method in BufferedImage that returns R G B and alpha values
                // encoded together in an integer
                int rgba = bi.getRGB(x, y);

                // Call the unpackPixel method to retrieve the four integers for
                // R, G, B and alpha and assign them each to their own integer
                int[] rgbValues = unpackPixel (rgba);

                int alpha = rgbValues[0]; // 0-255
                int red = rgbValues[1];
                int green = rgbValues[2];
                int blue = rgbValues[3];

                // make the pic BLUE-er
                if (blue < 253)
                    blue += 2;
                if (red >= 50)
                    red--;
                if (green >= 50)
                    green--;

                int newColour = packagePixel (red, green, blue, alpha);
                bi.setRGB (x, y, newColour);
            }
        }

    }

    public static void flipHorizontal (BufferedImage bi)
    {       
        BufferedImage tempBI = dCopy (bi);

        for(int x = 0; x<bi.getHeight(); x++){
            for(int y = 0; y< bi.getWidth(); y++){
                bi.setRGB(bi.getWidth() - y - 1, x, tempBI.getRGB(y,x));
            }
        }
    }

    public static void flipVerticle (BufferedImage bi){
        BufferedImage tempBI = dCopy (bi);

        for(int x = 0; x<bi.getWidth(); x++){
            for(int y = 0; y< bi.getHeight(); y++){
                bi.setRGB(x, y, tempBI.getRGB(x,bi.getHeight() - y -1));
            }
        }
    }

    public static BufferedImage rotateCw(BufferedImage bi){
        BufferedImage tempBI = new BufferedImage(bi.getHeight(), bi.getWidth(), 3);
        for(int x = 0; x<bi.getHeight(); x++){
            for(int y = 0; y< bi.getWidth(); y++){
                tempBI.setRGB((bi.getHeight() - 1) - x,y, bi.getRGB(y,x));
            }
        }
        return tempBI;

    }

    public static BufferedImage rotateCcw(BufferedImage bi){
        BufferedImage tempBI = new BufferedImage(bi.getHeight(), bi.getWidth(), 3);
        for(int x = 0; x<bi.getHeight(); x++){
            for(int y = 0; y< bi.getWidth(); y++){
                tempBI.setRGB(x, (bi.getWidth() - 1) - y, bi.getRGB(y,x));
            }
        }
        return tempBI;
    }

    public static BufferedImage rotate180(BufferedImage bi){
        BufferedImage tempBI = new BufferedImage(bi.getWidth(),bi.getHeight(),3);
        for(int x = 0; x<bi.getHeight(); x++){
            for(int y = 0; y< bi.getWidth(); y++){
                tempBI.setRGB((bi.getWidth() - 1) - y,(bi.getHeight() - 1) - x, bi.getRGB(y,x));
            }
        }
        return tempBI;
    }

    public static BufferedImage dCopy(BufferedImage bi){
        ColorModel cmode = bi.getColorModel();
        boolean isAlphaPremult = cmode.isAlphaPremultiplied();
        WritableRaster rast = bi.copyData(null);
        return new BufferedImage(cmode, rast, isAlphaPremult, null);
    }

    /**
     * Takes in a BufferedImage and returns a GreenfootImage.
     * 
     * @param newBi The BufferedImage to convert.
     * 
     * @return GreenfootImage   A GreenfootImage built from the BufferedImage provided.
     */
    public static GreenfootImage createGreenfootImageFromBI (BufferedImage newBi)
    {
        GreenfootImage returnImage = new GreenfootImage (newBi.getWidth(), newBi.getHeight());
        BufferedImage backingImage = returnImage.getAwtImage();
        Graphics2D backingGraphics = (Graphics2D)backingImage.getGraphics();
        backingGraphics.drawImage(newBi, null, 0, 0);

        return returnImage;
    }

    /**
     * Takes in an rgb value - the kind that is returned from BufferedImage's
     * getRGB() method - and returns 4 integers for easy manipulation.
     * 
     * By Jordan Cohen
     * Version 0.2
     * 
     * @param rgbaValue The value of a single pixel as an integer, representing<br>
     *                  8 bits for red, green and blue and 8 bits for alpha:<br>
     *                  <pre>alpha   red     green   blue</pre>
     *                  <pre>00000000000000000000000000000000</pre>
     * @return int[4]   Array containing 4 shorter ints<br>
     *                  <pre>0       1       2       3</pre>
     *                  <pre>alpha   red     green   blue</pre>
     */
    public static int[] unpackPixel (int rgbaValue)
    {
        int[] unpackedValues = new int[4];
        // alpha
        unpackedValues[0] = (rgbaValue >> 24) & 0xFF;
        // red
        unpackedValues[1] = (rgbaValue >> 16) & 0xFF;
        // green
        unpackedValues[2] = (rgbaValue >>  8) & 0xFF;
        // blue
        unpackedValues[3] = (rgbaValue) & 0xFF;

        return unpackedValues;
    }

    /**
     * Takes in a red, green, blue and alpha integer and uses bit-shifting
     * to package all of the data into a single integer.
     * 
     * @param   int red value (0-255)
     * @param   int green value (0-255)
     * @param   int blue value (0-255)
     * @param   int alpha value (0-255)
     * 
     * @return int  Integer representing 32 bit integer pixel ready
     *              for BufferedImage
     */
    public static int packagePixel (int r, int g, int b, int a)
    {
        int newRGB = (a << 24) | (r << 16) | (g << 8) | b;
        return newRGB;
    }
}

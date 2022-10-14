import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.awt.image.ColorModel;
import java.util.ArrayList;
/**
 * Starter code for Image Manipulation Array Assignment.
 * 
 * The class Processor contains all of the code to actually perform
 * transformation. The rest of the classes serve to support that
 * capability. This World allows the user to choose transformations
 * and open files.
 * 
 * Add to it and make it your own!
 * 
 * @author Jordan Cohen
 * @version November 2013
 */
public class Background extends World
{
    GreenfootImage bg = new GreenfootImage("image.png");
    // Constants:
    private final String STARTING_FILE = "colourful.jpg";
    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 720;

    // Objects and Variables:
    private ImageHolder image;
    private TextButton flipVert,pink,negative, red, green,sepia,brown,rotCw, rotCcw, rot180, save, collapse, expand, blueButton, hRevButton, openButton, undoButton, redoButton, yellow, darken, coolF, transparent;
    private SuperTextBox openFile;
    private String fileName;
    //Array list is used to store all of the altered images
    private ArrayList<BufferedImage> undo = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> redo = new ArrayList<BufferedImage>();
    //Variables below are used to track the amount of acts the user has manipulated the image which can be later used to determine if it is possible to undo or redo
    private boolean canUndo, cantUndo, cantRedo, newAct;
    private int totalActs;

    private BufferedImage undoImage;
    /**
     * Constructor for objects of class Background.
     * 
     */
    public Background()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        bg.scale(1024, 800);
        setBackground(bg);
        // Initialize buttons and the image --> Performed first so that the details can be retrieved and displayed
        image = new ImageHolder(STARTING_FILE); // The image holder constructor does the actual image loading

        // Set up UI elements --> See appropriate constructor API for details
        flipVert = new TextButton("Vert. Flip", 8, 80, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,14));  
        blueButton = new TextButton("Blueify", 8, 80, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        hRevButton = new TextButton("Hori. Flip", 8, 80, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,14));  
        openButton = new TextButton ("Open", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        save = new TextButton ("Save", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        collapse = new TextButton("Collapse", 8, 160, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        sepia = new TextButton ("Sepia", 8, 80, true, Color.BLACK, Color.ORANGE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        darken = new TextButton ("Darken", 8, 80, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        red = new TextButton ("Redify", 8, 80, true, Color.BLACK, Color.RED, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        brown = new TextButton ("Brownify", 8, 80, true, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        green = new TextButton ("Greenify", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        expand = new TextButton("Expand", 8, 160, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        rotCw = new TextButton ("CW", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        rotCcw = new TextButton ("CCW", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        rot180 = new TextButton ("Rotate 180", 8, 160, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        transparent = new TextButton ("Lower Transparency", 8, 160, true, Color.BLACK, Color.BLUE , Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,15));

        negative = new TextButton ("Negative", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        coolF = new TextButton ("Cool Filter", 8, 80, true, Color.BLACK, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16)); 
        pink = new TextButton ("Pinkify", 8, 80, true, Color.BLACK, Color.PINK, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        yellow = new TextButton ("Yellowify", 8, 80, true, Color.BLACK, Color.YELLOW, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        undoButton = new TextButton ("Undo", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));
        redoButton = new TextButton ("Redo", 8, 80, true, Color.BLACK, Color.GREEN, Color.WHITE, Color.WHITE, Color.BLACK, new Font ("Verdana",false ,false ,16));

        // Populate text box with details about the image
        openFile = new SuperTextBox(new String[]{"File: " + STARTING_FILE,"Scale: " + image.getScale() + " W: " + image.getRealWidth() + " H: " + image.getRealHeight()}, new Font ("Comic Sans MS", false, false, 16), 600, true);//new TextButton(" [ Open File: " + STARTING_FILE + " ] ");
        addObject (image, 430, 430);
        // Add objects to the screen
        addObject(collapse, 940, 160);
        addObject (blueButton, 900, 205);
        addObject(red, 980, 205);
        addObject(green, 900, 250);
        addObject(brown, 980, 250);
        addObject(pink, 900, 295);
        addObject(yellow, 980, 295);
        addObject(sepia, 900, 340);
        addObject(darken, 980, 340); 
        addObject(negative, 900, 385);
        addObject(coolF, 980, 385);
        addObject(rotCw, 900, 430);
        addObject(rotCcw, 980, 430);
        addObject(transparent, 940, 475);

        addObject (rot180, 940, 520);
        addObject(hRevButton, 980, 565);
        addObject(flipVert, 900,565);
        addObject(undoButton, 900, 610);
        addObject(redoButton, 980, 610);

        // place the open file text box in the top left corner
        addObject (openFile, 300,160);
        // place the open file button directly beside the open file text box
        addObject (openButton, 645, 160);
        addObject(save, 725, 160);
        totalActs = 0;
        cantUndo = false;
        newAct = false;

        undo.add(deepCopy(image.getBufferedImage()));
        redo.add(deepCopy(image.getBufferedImage()));

        prepare();
    }

    /**
     * Act() method just checks for mouse input
     */
    public void act ()
    {
        checkMouse();
    }

    /**
     * Check for user clicking on a button
     */
    private void checkMouse ()
    {
        // Avoid excess mouse checks - only check mouse if somethething is clicked.
        if (Greenfoot.mouseClicked(null))
        {
            if(Greenfoot.mouseClicked(collapse)){
                removeObject(blueButton);
                removeObject(red);
                removeObject(green);
                removeObject(negative);
                removeObject(coolF);
                removeObject(sepia);
                removeObject(darken);
                removeObject(brown);
                removeObject(rotCw);
                removeObject(rotCcw);
                removeObject(rot180);
                removeObject(transparent);
                removeObject(pink);
                removeObject(hRevButton);
                removeObject(flipVert);
                removeObject(undoButton);
                removeObject(redoButton);
                removeObject(collapse);
                removeObject(yellow);
                addObject(expand, 940, 160); 
            }
            if(Greenfoot.mouseClicked(expand)){
                addObject (image, 430, 430);
                addObject (collapse,940, 160);
                addObject (blueButton, 900, 205);
                addObject (red,980,205);
                addObject (green,900,250);
                addObject (pink,900,295);
                addObject (yellow, 980, 295);
                addObject (brown,980,250);
                addObject (sepia, 900 ,340);
                addObject(darken, 980, 340);
                addObject (negative,900,385);
                addObject (coolF, 980, 385);
                addObject (rot180, 940, 520); 
                addObject (hRevButton, 900,565);
                addObject (flipVert, 980,565);
                addObject (rotCw,900,430);
                addObject (rotCcw,980,430);
                addObject (transparent,940,475);
                addObject (undoButton,900,610);
                addObject (redoButton,980,610);
                removeObject(expand);
            }
            else if (Greenfoot.mouseClicked(save)){
                saveFile();
            }
            else if (Greenfoot.mouseClicked(blueButton)){

                Processor.blueify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;
            }
            else if (Greenfoot.mouseClicked(red)){

                Processor.redify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(green)){

                Processor.greenify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(pink)){

                Processor.pinkify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(brown)){

                Processor.brownify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(sepia)){
                Processor.sepia(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(negative)){
                Processor.negative(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(rotCw)){
                image.setNewImage(createGreenfootImageFromBI(Processor.rotateCw(image.getBufferedImage())));
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(rotCcw)){
                image.setNewImage(createGreenfootImageFromBI(Processor.rotateCcw(image.getBufferedImage())));
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }            else if (Greenfoot.mouseClicked(rot180)){
                image.setNewImage(createGreenfootImageFromBI(Processor.rotate180(image.getBufferedImage())));
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }            else if (Greenfoot.mouseClicked(flipVert)){

                Processor.flipVerticle(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(hRevButton)){
                Processor.flipHorizontal(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
            else if (Greenfoot.mouseClicked(openButton))
            {
                openFile ();
            }
            else if(Greenfoot.mouseClicked(undoButton)){
                if(totalActs >= 0 ){
                    if(totalActs ==0){
                        undoImage = undo.get(totalActs);
                    }
                    else{
                        undoImage = undo.get(totalActs -1);
                    }
                    image.setNewImage(createGreenfootImageFromBI(undoImage));
                    undo.remove(totalActs);
                    totalActs--;
                    canUndo = true;
                }
                openFile.update (image.getDetails ());
            }
            else if(Greenfoot.mouseClicked(redoButton)){
                if(newAct == true){
                    redo.remove(totalActs+1);
                }
                else if(redo.size() != 1 && totalActs + 1 != redo.size()){
                    undoImage = redo.get(totalActs + 1);
                    undo.add(deepCopy(image.getBufferedImage()));
                    image.setNewImage(createGreenfootImageFromBI(undoImage));
                    totalActs++;
                }
            }else if(Greenfoot.mouseClicked(yellow)){
                Processor.yellowify(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;
            }else if (Greenfoot.mouseClicked(darken)){

                Processor.darken(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }else if (Greenfoot.mouseClicked(coolF)){
                Processor.coolFilter(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }else if (Greenfoot.mouseClicked(transparent)){

                Processor.transparency(image.getBufferedImage());
                image.redraw();
                openFile.update (image.getDetails ());
                undo.add(deepCopy(image.getBufferedImage()));
                redo.add(deepCopy(image.getBufferedImage()));
                totalActs++;
                if(canUndo == true){
                    newAct = true;
                }
                canUndo = false;

            }
        }
    }

    // Code provided, but not yet implemented - This will save image as a png.
    private void saveFile () {
        try{
            // This will pop up a text input box - You should improve this with a JFileChooser like for the open function
            String fileName = JOptionPane.showInputDialog("Input file name (no extension)");

            fileName += ".png";
            File f = new File (fileName);  
            ImageIO.write(image.getBufferedImage(), "png", f); 

        }
        catch (IOException e){
            // this code instead
            System.out.println("Unable to save file: " + e);
        }
    }

    /**
     * Allows the user to open a new image file.
     */
    private void openFile ()
    {
        // Create a UI frame (required to show a UI element from Java.Swing)
        JFrame frame = new JFrame();
        // Create a JFileChooser, a file chooser box included with Java 
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            if (image.openFile (selectedFile.getAbsolutePath(), selectedFile.getName()))
            {
                //String display = " [ Open File: " + selectedFile.getName() + " ] ";
                openFile.update (image.getDetails ());
            }
        }
        // If the file opening operation is successful, update the text in the open file button
        /**if (image.openFile (fileName))
        {
        String display = " [ Open File: " + fileName + " ] ";
        openFile.update (display);
        }*/

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

    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultip = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultip, null);
    }

    private void prepare(){

    }

}


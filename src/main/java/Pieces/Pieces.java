



package Piece;

import Schach.Board;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Pieces  {

    public int col,row;
    public int posx,posy;

    public boolean isWhite;
    public String name;
    public int value;

    public boolean isFirstMove = true;



    BufferedImage Sheet;


    {
        try {
            Sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("pieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected int sheetscale = Sheet.getWidth()/6;
    Image sprite;
    Board board;


    public Pieces(Board board) {
        this.board = board;

    }



    public boolean isValidmove( int col, int row) {return  true;}
    public boolean Blocked(int col, int row) {return  false;}

    public void paint(Graphics2D g2D){
        g2D.drawImage(sprite,posx,posy,null);




    }

}








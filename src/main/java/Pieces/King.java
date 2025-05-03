package Piece;

import Schach.Board;


import javax.swing.*;
import java.awt.image.BufferedImage;

public class King extends Pieces {

    private static JFrame frm;
    public int tileSize = 110;

    public static JLabel lbl_Knight;
    public King(Board board1, int col, int row, boolean isWhite) {
        super(board1);


        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.posx = col * board1.tileSize ;
        this.posy = row * board1.tileSize ;
        this.name = "King";
        this.sprite = Sheet.getSubimage(0* sheetscale,isWhite ? 0:sheetscale,sheetscale,sheetscale).getScaledInstance(board1.tileSize,board1.tileSize, BufferedImage.SCALE_SMOOTH);

    }


    public boolean isValidmove( int col, int row) {
        return Math.abs((col - this.col) * (row - this.row)) == 1 || Math.abs(col - this.col) + Math.abs(row - this.row) == 1  ;
    }


}
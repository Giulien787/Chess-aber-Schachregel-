package Piece;

import Schach.Board;


import javax.swing.*;
import java.awt.image.BufferedImage;


public class Bishop extends Pieces {

    private static JFrame frm;
    public int tileSize = 110;

    public static JLabel lbl_Knight;

    public Bishop(Board board1, int col, int row, boolean isWhite) {
        super(board1);


        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.posx = col * board1.tileSize;
        this.posy = row * board1.tileSize;
        this.name = "Bishop";
        this.sprite = Sheet.getSubimage(2 * sheetscale, isWhite ? 0 : sheetscale, sheetscale, sheetscale).getScaledInstance(board1.tileSize, board1.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidmove(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    public boolean Blocked(int col, int row) {


        //links oben
        if (this.col > col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col) ; i++ ) {
                if (board.getPieces(this.col -i , this.row -i) != null) {


                    return true;
                }

            }
        }

        //rechts oben
        if (this.col < col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col) ; i++ ) {
                if (board.getPieces(this.col +i , this.row -i) != null) {


                    return true;
                }

            }
        }
        //links unten
        if (this.col > col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col) ; i++ ) {
                if (board.getPieces(this.col -i , this.row +i) != null) {


                    return true;
                }

            }
        }

        //rechts unten
        if (this.col < col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col) ; i++ ) {
                if (board.getPieces(this.col +i , this.row +i) != null) {


                    return true;
                }

            }
        }


        return false;
    }
}
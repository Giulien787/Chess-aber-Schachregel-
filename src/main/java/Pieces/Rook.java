package Piece;

import Schach.Board;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Rook extends Pieces {

    private static JFrame frm;
    public int tileSize = 110;

    public static JLabel lbl_Knight;

    public Rook(Board board1, int col, int row, boolean isWhite) {
        super(board1);


        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.posx = col * board1.tileSize;
        this.posy = row * board1.tileSize;
        this.name = "Rook";
        this.sprite = Sheet.getSubimage(4 * sheetscale, isWhite ? 0 : sheetscale, sheetscale, sheetscale).getScaledInstance(board1.tileSize, board1.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidmove(int col, int row) {

        return this.col == col || this.row == row;

    }

    public boolean Blocked(int col, int row) {


        //links
        if (this.col > col) {
            for (int c = this.col - 1; c > col; c--) {      // -1 weil ganz links 0|0 ist ist also weiter nach links
                if (board.getPieces(c, this.row) != null) {


                    return true;
                }

            }
        }

        //rechts
        if (this.col < col) {
            for (int c = this.col +1; c < col; c++) {      // +1 weil ganz links 0|0 ist also weiter nach rechts
                if (board.getPieces(c, this.row) != null) {
                    return true;
                }

            }

        }

        //oben
        if (this.row > row) {
            for (int r = this.row -1; r > row; r--) {      // +1 weil ganz links 0|0 ist also weiter nach rechts
                if (board.getPieces( this.col,r) != null) {
                    return true;
                }

            }

        }

        //unten
        if (this.row < row) {
            for (int r = this.row + 1; r < row; r++) {      // -1 weil ganz links 0|0 ist ist also weiter nach links
                if (board.getPieces( this.col,r) != null) {


                    return true;
                }

            }
        }





        return false;

        }






    }

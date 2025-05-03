package Piece;

import Schach.Board;


import javax.swing.*;
import java.awt.image.BufferedImage;


public class Pawn extends Pieces {

    private static JFrame frm;
    public int tileSize = 110;

    public static JLabel lbl_Knight;
    public Pawn(Board board1, int col, int row, boolean isWhite) {
        super(board1);


        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.posx = col * board1.tileSize ;
        this.posy = row * board1.tileSize ;
        this.name = "Pawn";
        this.sprite = Sheet.getSubimage(5* sheetscale,isWhite ? 0:sheetscale,sheetscale,sheetscale).getScaledInstance(board1.tileSize,board1.tileSize, BufferedImage.SCALE_SMOOTH);



    }
    public boolean isValidmove( int col, int row) {


        int colorIndex;
        if (isWhite) {
            colorIndex = 1;
        } else {
            colorIndex = -1;
        }

            // push pawn 1 Feld vorwärts
            if (this.col == col && row == this.row - colorIndex && board.getPieces(col, row) == null)
                return true;

            // push pawn 2 Felder vorwärts beim ersten Zug
            if (isFirstMove && this.col == col && row == this.row - colorIndex * 2
                    && board.getPieces(col, row) == null && board.getPieces(col, row + colorIndex) == null)
                return true;

            // gegnerische Figur diagonal links schlagen
            if (col == this.col - 1 && row == this.row - colorIndex && board.getPieces(col, row) != null)
                return true;

            // gegnerische Figur diagonal rechts schlagen
            if (col == this.col + 1 && row == this.row - colorIndex && board.getPieces(col, row) != null)
                return true;

            return false;

        }
        /*
        Erklärung:
        Diese Methode isValidMovement überprüft, ob ein Bauernzug im Schach gültig ist:

        Der Bauer darf ein Feld geradeaus ziehen, wenn dieses leer ist.
                Beim ersten Zug darf er zwei Felder ziehen, wenn beide Felder leer sind.
        Diagonal nach vorne links oder rechts darf er nur ziehen, wenn er eine gegnerische Figur schlagen kann.
        Die Variable colorIndex entscheidet je nach Farbe, ob der Bauer nach oben oder unten zieht (1 für Weiß, -1 für Schwarz).
*/

    }





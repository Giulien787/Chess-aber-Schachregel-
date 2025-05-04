package Pieces;

import Schach.Board;
import Schach.Move;

import javax.swing.*;
import java.awt.image.BufferedImage;


public class King extends Schachfiguren {

    // (Aktuell ungenutzt) Referenz auf das Fenster – kann ggf. entfernt werden
    private static JFrame frm;

    // Größe eines Feldes auf dem Schachbrett (könnte auch direkt vom Board verwendet werden)
    public int tileSize = 110;

    // (Aktuell ungenutzt) GUI-Element – vermutlich ein Überbleibsel aus einer anderen Klasse
    public static JLabel lbl_Knight;

    /*
     * Konstruktor zur Initialisierung des Königs.
     *
     * board1  Referenz auf das Spielfeld

     * isWhite true, wenn die Figur weiß ist, sonst schwarz
     */
    public King(Board board1, int col, int row, boolean isWhite) {
        super(board1);

        // Grundlegende Position und Farbe setzen
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        // Position in Pixel berechnen
        this.posx = col * board1.tileSize;
        this.posy = row * board1.tileSize;

        // Name und Sprite setzen
        this.name = "King";
        this.sprite = Sheet.getSubimage(
                0 * sheetscale,                        // X-Position im Sprite-Sheet (0 = König)
                isWhite ? 0 : sheetscale,              // Y-Position: 0 = weiß, sonst schwarz
                sheetscale, sheetscale                 // Breite und Höhe
        ).getScaledInstance(board1.tileSize, board1.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    /*
     * Prüft, ob der Zug gültig ist. Der König darf genau ein Feld
     * in jede Richtung ziehen (horizontal, vertikal oder diagonal).
     *
     * col Zielspalte
     * row Zielzeile
     * return true, wenn der Zug legal ist, sonst false
     */
    public boolean isValidmove(int col, int row) {

        // Math.abs sorgt dafür, dass Vorzeichen keine Rolle spielen – nur die Entfernung zählt.
        return Math.abs((col - this.col) * (row - this.row)) == 1
                || Math.abs(col - this.col) + Math.abs(row - this.row) == 1
                || castle(col, row);
    }

    private boolean castle ( int col, int row ) {

        if (this.row == row) {
            if (col == 6) {
                Schachfiguren Rook = board.getPieces(7, row);
                if (Rook != null && Rook.isFirstMove && isFirstMove) {   //das zweite isFirstMove gehört zu King weil wir in der King Klasse sind
                    return board.getPieces(5, row) == null && board.getPieces(6, row) == null && !board.ripking.ripKing(new Move(board, this, 5, row));
                }
            } else if (col == 2) {
                Schachfiguren Rook = board.getPieces(0, row);
                if (Rook != null && Rook.isFirstMove && isFirstMove) {   //das zweite isFirstMove gehört zu King weil wir in der King Klasse sind
                    return board.getPieces(3, row) == null && board.getPieces(2, row) == null && !board.ripking.ripKing(new Move(board, this, 1, row));

                }
            }
        }

        return false;
    }
}


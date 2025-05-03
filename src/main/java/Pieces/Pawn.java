package Pieces;

import Schach.Board;

import javax.swing.*;
import java.awt.image.BufferedImage;


public class Pawn extends Pieces {

    // (Derzeit ungenutzt) Fensterreferenz – kann ggf. entfernt werden
    private static JFrame frm;

    // Größe eines Feldes auf dem Schachbrett
    public int tileSize = 110;

    // (Aktuell ungenutztes Label)
    public static JLabel lbl_Knight;

    /**
     * Konstruktor zur Initialisierung des Bauern.
     *
     * board1  Referenz auf das Schachbrett
     * col     Spalte (0–7)
     * row     Zeile (0–7)
     * isWhite true, wenn der Bauer weiß ist, sonst schwarz
     */
    public Pawn(Board board1, int col, int row, boolean isWhite) {
        super(board1);

        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        // Grafische Position bestimmen
        this.posx = col * board1.tileSize;
        this.posy = row * board1.tileSize;

        // Name und Figurengrafik setzen
        this.name = "Pawn";
        this.sprite = Sheet.getSubimage(
                5 * sheetscale,                        // X-Position im Sprite-Sheet (5 = Bauer)
                isWhite ? 0 : sheetscale,              // Y-Position: abhängig von der Farbe
                sheetscale, sheetscale                 // Breite und Höhe
        ).getScaledInstance(board1.tileSize, board1.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    /**
     * Prüft, ob ein gegebener Zug für den Bauern gültig ist.
     * Die Zugregeln richten sich nach Farbe und Position:
     *
     *   Ein Feld geradeaus, wenn leer
     *   Zwei Felder geradeaus beim ersten Zug, wenn beide Felder leer
     *   Diagonal schlagen, wenn dort eine gegnerische Figur steht
     *
     * col Zielspalte
     * row Zielzeile
     * true, wenn der Zug gültig ist, sonst false
     */
    public boolean isValidmove(int col, int row) {

        // Richtung des Bauernzugs abhängig von der Farbe:
        // Weiß bewegt sich nach oben (nach niedrigeren Zeilen = -1),
        // Schwarz nach unten (nach höheren Zeilen = +1)
        int colorIndex = isWhite ? 1 : -1;

        // 1 Feld geradeaus, wenn Ziel leer ist
        if (this.col == col &&
                row == this.row - colorIndex &&
                board.getPieces(col, row) == null) {
            return true;
        }

        // 2 Felder geradeaus beim ersten Zug, wenn beide Felder leer sind
        if (isFirstMove &&
                this.col == col &&
                row == this.row - 2 * colorIndex &&
                board.getPieces(col, row) == null &&
                board.getPieces(col, row + colorIndex) == null) {
            return true;
        }

        // Schlagen nach vorne-links
        if (col == this.col - 1 &&
                row == this.row - colorIndex &&
                board.getPieces(col, row) != null) {
            return true;
        }

        // Schlagen nach vorne-rechts
        if (col == this.col + 1 &&
                row == this.row - colorIndex &&
                board.getPieces(col, row) != null) {
            return true;
        }

        // Kein gültiger Zug
        return false;
    }
}

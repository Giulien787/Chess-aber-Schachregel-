package Pieces;

import Schach.Board;

import javax.swing.*;
import java.awt.image.BufferedImage;


public class Knight extends Schachfiguren {

    // (Aktuell ungenutzt) Referenz auf ein Fenster – kann entfernt werden, wenn nicht verwendet
    private static JFrame frm;

    // Größe eines Feldes auf dem Schachbrett
    public int tileSize = 110;

    // (Aktuell ungenutzt) Label – Überbleibsel oder potentiell GUI-relevant
    public static JLabel lbl_Knight;

    /*
     * Konstruktor zur Initialisierung des Springers.
     *
     * board1  Referenz auf das Schachbrett
     * col     Spalte (0–7)
     * row     Zeile (0–7)
     * isWhite true, wenn die Figur weiß ist, sonst schwarz
     */
    public Knight(Board board1, int col, int row, boolean isWhite) {
        super(board1);

        // Logische Position setzen
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        // Grafische Position berechnen
        this.posx = col * board1.tileSize;
        this.posy = row * board1.tileSize;

        // Name und Bild des Springers festlegen
        this.name = "Knight";
        this.sprite = Sheet.getSubimage(
                3 * sheetscale,                        // X-Position im Sprite-Sheet (3 = Springer)
                isWhite ? 0 : sheetscale,              // Y-Position: abhängig von der Farbe
                sheetscale, sheetscale                 // Breite und Höhe des Bildes
        ).getScaledInstance(board1.tileSize, board1.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    /*
     * Überprüft, ob der Springerzug gültig ist.
     * Er bewegt sich in L-Form: 2 Felder in eine Richtung und 1 in die andere.
     *
     * col Zielspalte
     * row Zielzeile
     * true, wenn der Zug legal ist, sonst false
     */
    public boolean isValidmove(int col, int row) {
        // Der Springerzug ist gültig, wenn das Produkt der absoluten Differenzen 2 ergibt:
        // Mögliche Kombinationen sind (2,1) oder (1,2):

        return Math.abs(row - this.row) * Math.abs(col - this.col) == 2;
    }
}

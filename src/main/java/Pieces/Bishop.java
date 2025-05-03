package Pieces;

import Schach.Board;

import javax.swing.*;
import java.awt.image.BufferedImage;


public class Bishop extends Pieces {

    // (Aktuell ungenutzt) Referenz auf ein Fenster – kann entfernt werden, falls unnötig
    private static JFrame frm;

    // Größe eines einzelnen Feldes auf dem Schachbrett (könnte auch aus Board übernommen werden)
    public int tileSize = 110;

    // (Aktuell ungenutzt) Label-Referenz – vermutlich ein Überbleibsel von einem anderen Stück
    public static JLabel lbl_Knight;

    /**
     * Konstruktor für den Läufer.
     *
     * @param board1  Referenz auf das Spielfeld

     * @param isWhite True, wenn die Figur weiß ist; sonst schwarz
     */
    public Bishop(Board board1, int col, int row, boolean isWhite) {
        super(board1);

        // Position auf dem Schachbrett setzen
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;

        // Grafische Position (in Pixeln) berechnen
        this.posx = col * board1.tileSize;
        this.posy = row * board1.tileSize;

        // Namen und grafisches Sprite der Figur setzen
        this.name = "Bishop";
        this.sprite = Sheet.getSubimage(
                2 * sheetscale,                      // X-Koordinate im Sprite-Sheet (2 = Läufer)
                isWhite ? 0 : sheetscale,            // Y-Koordinate (weiß = 0, schwarz = sheetscale)
                sheetscale, sheetscale               // Breite und Höhe des Bildausschnitts
        ).getScaledInstance(board1.tileSize, board1.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    /**
     * Überprüft, ob der Zug diagonal verläuft – nur dann ist er für einen Läufer gültig.
     *
     * col Zielspalte
     * row Zielzeile
     * return true, wenn der Zug diagonal ist, sonst false
     */
    public boolean isValidmove(int col, int row) {
        // Der Läufer darf sich nur diagonal bewegen:
        // Math.abs(...) macht die Richtung unwichtig – es zählt nur die Anzahl der Felder.
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    /**
     * Prüft, ob auf dem Weg zur Zielposition eine andere Figur den Läufer blockiert.
     *
     * col Zielspalte
     * row Zielzeile
     * return true, wenn der Weg blockiert ist, sonst false
     */
    public boolean Blocked(int col, int row) {

        // Diagonale nach links oben
        if (this.col > col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                // Math.abs stellt sicher, dass unabhängig von der Richtung die Schleife korrekt läuft
                if (board.getPieces(this.col - i, this.row - i) != null) {
                    return true; // Figur im Weg
                }
            }
        }

        // Diagonale nach rechts oben
        if (this.col < col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPieces(this.col + i, this.row - i) != null) {
                    return true;
                }
            }
        }

        // Diagonale nach links unten
        if (this.col > col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPieces(this.col - i, this.row + i) != null) {
                    return true;
                }
            }
        }

        // Diagonale nach rechts unten
        if (this.col < col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPieces(this.col + i, this.row + i) != null) {
                    return true;
                }
            }
        }

        // Kein Hindernis auf dem Weg
        return false;
    }
}

package Pieces;

import Schach.Board;

import javax.swing.*;
import java.awt.image.BufferedImage;


public class Rook extends Pieces {

    private static JFrame frm;
    public int tileSize = 110;

    public static JLabel lbl_Knight;

    /**
     * Konstruktor: Erstellt einen Turm an der gegebenen Position.
     *
     * board1   Das Spielfeld
     * col      Spalte des Turms
     * row      Zeile des Turms
     * isWhite  Farbe des Turms (weiß oder schwarz)
     */
    public Rook(Board board1, int col, int row, boolean isWhite) {
        super(board1);

        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.posx = col * board1.tileSize;
        this.posy = row * board1.tileSize;
        this.name = "Rook";
        // Lade und skaliere das richtige Sprite basierend auf Farbe
        this.sprite = Sheet.getSubimage(4 * sheetscale, isWhite ? 0 : sheetscale, sheetscale, sheetscale)
                .getScaledInstance(board1.tileSize, board1.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    /**
     * Prüft, ob der Zug in einer legalen Richtung für einen Turm erfolgt.
     * Gültige Richtungen: gleiche Zeile (horizontal) oder gleiche Spalte (vertikal).
     *
     * col Zielspalte
     * row Zielzeile
     * return true, wenn der Zug vertikal oder horizontal ist
     */
    public boolean isValidmove(int col, int row) {
        return this.col == col || this.row == row;
    }

    /**
     * Überprüft, ob zwischen aktueller Position und Ziel eine andere Figur steht.
     * Der Turm darf nicht über andere Figuren hinwegziehen.
     *
     * col Zielspalte
     * row Zielzeile
     * return true, wenn der Weg blockiert ist
     */
    public boolean Blocked(int col, int row) {

        // Horizontal nach links
        if (this.col > col) {
            for (int c = this.col - 1; c > col; c--) {
                if (board.getPieces(c, this.row) != null) {
                    return true;
                }
            }
        }

        // Horizontal nach rechts
        if (this.col < col) {
            for (int c = this.col + 1; c < col; c++) {
                if (board.getPieces(c, this.row) != null) {
                    return true;
                }
            }
        }

        // Vertikal nach oben
        if (this.row > row) {
            for (int r = this.row - 1; r > row; r--) {
                if (board.getPieces(this.col, r) != null) {
                    return true;
                }
            }
        }

        // Vertikal nach unten
        if (this.row < row) {
            for (int r = this.row + 1; r < row; r++) {
                if (board.getPieces(this.col, r) != null) {
                    return true;
                }
            }
        }

        // Kein Hindernis gefunden
        return false;
    }
}

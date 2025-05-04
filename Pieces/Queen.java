package Pieces;

import Schach.Board;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Queen extends Schachfiguren {

    private static JFrame frm;
    public int tileSize = 110;

    public static JLabel lbl_Knight;

    /*
     * Konstruktor: Initialisiert eine Dame an der gegebenen Position.
     *
     * board1   Referenz auf das Schachbrett
     * col      Spaltenposition
     * row      Zeilenposition
     * isWhite  true, wenn Figur weiß ist; false für schwarz
     */
    public Queen(Board board1, int col, int row, boolean isWhite) {
        super(board1);

        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.posx = col * board1.tileSize;
        this.posy = row * board1.tileSize;
        this.name = "Queen";
        // Wählt das richtige Sprite für die Farbe und skaliert es passend
        this.sprite = Sheet.getSubimage(1 * sheetscale, isWhite ? 0 : sheetscale, sheetscale, sheetscale)
                .getScaledInstance(board1.tileSize, board1.tileSize, BufferedImage.SCALE_SMOOTH);
    }

    /*
     * Prüft, ob ein Zug der Dame gültig ist.
     * Gültige Richtungen: beliebig viele Felder vertikal, horizontal oder diagonal.
     *
     * col Zielspalte
     * row Zielzeile
     * true, wenn der Zug den Regeln der Dame entspricht
     */
    public boolean isValidmove(int col, int row) {
        // Zug ist gültig, wenn entweder:
        // - die Differenz der Spalten und Zeilen gleich ist (Diagonale)
        // - oder gleiche Spalte bzw. gleiche Zeile (gerade Bewegung)
        return Math.abs(this.col - col) == Math.abs(this.row - row) || this.col == col || this.row == row;
    }

    /*
     * Prüft, ob eine andere Figur zwischen aktueller Position und Zielposition steht.
     * Die Dame darf nicht über andere Figuren hinwegziehen.
     *
     * col Zielspalte
     * row Zielzeile
     * return true, wenn der Weg blockiert ist
     */
    public boolean Blocked(int col, int row) {

        // Falls es eine gerade Bewegung ist (horizontal oder vertikal):
        if (this.col == col || this.row == row) {

            // Links (horizontal nach links)
            if (this.col > col) {
                for (int c = this.col - 1; c > col; c--) {
                    if (board.getPieces(c, this.row) != null) {
                        return true;
                    }
                }
            }

            // Rechts (horizontal nach rechts)
            if (this.col < col) {
                for (int c = this.col + 1; c < col; c++) {
                    if (board.getPieces(c, this.row) != null) {
                        return true;
                    }
                }
            }

            // Oben (vertikal nach oben)
            if (this.row > row) {
                for (int r = this.row - 1; r > row; r--) {
                    if (board.getPieces(this.col, r) != null) {
                        return true;
                    }
                }
            }

            // Unten (vertikal nach unten)
            if (this.row < row) {
                for (int r = this.row + 1; r < row; r++) {
                    if (board.getPieces(this.col, r) != null) {
                        return true;
                    }
                }
            }

        } else {
            // Diagonale Bewegungen:

            // Links oben
            if (this.col > col && this.row > row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPieces(this.col - i, this.row - i) != null) {
                        return true;
                    }
                }
            }

            // Rechts oben
            if (this.col < col && this.row > row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPieces(this.col + i, this.row - i) != null) {
                        return true;
                    }
                }
            }

            // Links unten
            if (this.col > col && this.row < row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPieces(this.col - i, this.row + i) != null) {
                        return true;
                    }
                }
            }

            // Rechts unten
            if (this.col < col && this.row < row) {
                for (int i = 1; i < Math.abs(this.col - col); i++) {
                    if (board.getPieces(this.col + i, this.row + i) != null) {
                        return true;
                    }
                }
            }
        }

        // Kein Hindernis gefunden
        return false;
    }
}

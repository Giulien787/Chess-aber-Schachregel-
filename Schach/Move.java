package Schach;

import Pieces.Schachfiguren;

public class Move {

    int older_row; // Ursprüngliche Zeilenposition der Figur
    int newer_row; // Neue Zeilenposition nach dem Zug

    int older_col; // Ursprüngliche Spaltenposition der Figur
    int newer_col; // Neue Spaltenposition nach dem Zug

    Schachfiguren piece; // Die bewegte Figur

    Schachfiguren gefangen; // Eventuell geschlagene Figur an der Zielposition

    // Konstruktor für einen Zug
    public Move(Board board, Schachfiguren piece, int newer_row, int newer_col){

        this.older_row = older_row; // Ursprüngliche Zeile (wird vermutlich an anderer Stelle gesetzt)
        this.newer_row = newer_row; // Zielzeile

        this.older_col = older_col; // Ursprüngliche Spalte (wird vermutlich an anderer Stelle gesetzt)
        this.newer_col = newer_col; // Zielspalte

        this.piece = piece; // Referenz auf die bewegte Figur

        // Ermittelt, ob sich am Zielort eine Figur befindet, die geschlagen wird
        this.gefangen = board.getPieces(newer_col, newer_row);
    }

}

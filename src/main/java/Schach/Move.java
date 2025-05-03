package de.chemeker.Schach;

import de.chemeker.Piece.Pieces;
import de.chemeker.Schach.*;

public class Move {


    int older_row;
    int newer_row;

    int older_col;
    int newer_col;

    Pieces piece;

    Pieces gefangen;

    public Move( Board board, Pieces piece, int newer_row, int newer_col){

        this.older_row = older_row;
        this.newer_row = newer_row;

        this.older_col = older_col;
        this.newer_col = newer_col;


        this.piece = piece;
        this.gefangen = board.getPieces(newer_col,newer_row);




    }









































}



/*

package Schach;

import Pieces.Pieces;

public class ripKing {

    Board board;

    public ripKing(Board board) {
        this.board = board;
    }

    public boolean ripKing(Move move) {
        Pieces King = board.ripKing(move.piece.isWhite);
        assert King != null;

        int kingCol = King.col;
        int kingRow = King.row;

        // Wenn der König selbst bewegt wird
        if (board.selectedPieces != null && board.selectedPieces.name.equals("Pieces.King")) {
            kingCol = move.newer_col;
            kingRow = move.newer_row;
        }

        // Prüfe auf Bedrohungen durch alle Figurtypen
        return hitByRook(kingCol, kingRow, King, 0, 1) ||   // hoch
                hitByRook(kingCol, kingRow, King, 1, 0) ||   // rechts
                hitByRook(kingCol, kingRow, King, 0, -1) ||  // runter
                hitByRook(kingCol, kingRow, King, -1, 0) ||  // links

                hitByBishop(kingCol, kingRow, King, -1, -1) ||  // links oben
                hitByBishop(kingCol, kingRow, King, 1, -1) ||   // rechts oben
                hitByBishop(kingCol, kingRow, King, 1, 1) ||    // rechts unten
                hitByBishop(kingCol, kingRow, King, -1, 1) ||   // links unten

                hitByKnight(kingCol, kingRow, King) ||
                hitByPawn(kingCol, kingRow, King) ||
                hitByKing(kingCol, kingRow, King);
    }

    private boolean hitByRook(int kingCol, int kingRow, Pieces king, int colStep, int rowStep) {
        for (int i = 1; i < 8; i++) {
            int newCol = kingCol + (i * colStep);
            int newRow = kingRow + (i * rowStep);
            Pieces piece = board.getPieces(newCol, newRow);

            if (piece != null) {
                if (!board.sameTeam(piece, king) &&
                        (piece.name.equals("Pieces.Rook") || piece.name.equals("Pieces.Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByBishop(int kingCol, int kingRow, Pieces king, int colStep, int rowStep) {
        for (int i = 1; i < 8; i++) {
            int newCol = kingCol + (i * colStep);
            int newRow = kingRow + (i * rowStep);
            Pieces piece = board.getPieces(newCol, newRow);

            if (piece != null) {
                if (!board.sameTeam(piece, king) &&
                        (piece.name.equals("Pieces.Bishop") || piece.name.equals("Pieces.Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByKnight(int kingCol, int kingRow, Pieces king) {
        int[][] knightMoves = {
                {-1, -2}, {1, -2}, {2, -1}, {2, 1},
                {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}
        };

        for (int[] move : knightMoves) {
            Pieces piece = board.getPieces(kingCol + move[0], kingRow + move[1]);
            if (checkKnight(piece, king)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkKnight(Pieces p, Pieces k) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("Pieces.Knight");
    }

    private boolean hitByKing(int kingCol, int kingRow, Pieces king) {
        int[][] directions = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1,  0},          {1,  0},
                {-1,  1}, {0,  1}, {1,  1}
        };

        for (int[] dir : directions) {
            int newCol = kingCol + dir[0];
            int newRow = kingRow + dir[1];
            Pieces piece = board.getPieces(newCol, newRow);
            if (checkKing(piece, king)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkKing(Pieces p, Pieces k) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("Pieces.King");
    }

    private boolean hitByPawn(int kingCol, int kingRow, Pieces king) {
        int dir = king.isWhite ? -1 : 1;

        Pieces left = board.getPieces(kingCol - 1, kingRow + dir);
        Pieces right = board.getPieces(kingCol + 1, kingRow + dir);

        return checkPawn(left, king) || checkPawn(right, king);
    }

    private boolean checkPawn(Pieces p, Pieces k) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("Pieces.Pawn");
    }
}*/
package Schach;

import Pieces.Pieces;


public class RipKing {


    Board board ;
    public RipKing(Board board){
        this.board = board;


    }

    public boolean ripKing(Move move) {
        Pieces king = board.ripKing(move.piece.isWhite);
        assert king != null;
        int kingCol = king.col;
        int kingRow = king.row;
        if (board.selectedPieces != null && board.selectedPieces.name.equals("King")) {
            kingCol = move.newer_col;
            kingRow = move.newer_row;


        }
        return hitByRook(move.newer_col, move.newer_row, king, kingCol, kingRow, 0, 1)||   //für vertikal (hoch)
                hitByRook(move.newer_col, move.newer_row, king, kingCol, kingRow, 1, 0)||
                hitByRook(move.newer_col, move.newer_row, king, kingCol, kingRow, 0, -1)||
                hitByRook(move.newer_col, move.newer_row, king, kingCol, kingRow, -1, 0)||

                hitByBishop(move.newer_col, move.newer_row, king, kingCol, kingRow, -1, -1)||
                hitByBishop(move.newer_col, move.newer_row, king, kingCol, kingRow, 1, -1)||
                hitByBishop(move.newer_col, move.newer_row, king, kingCol, kingRow, 1, 1)||
                hitByBishop(move.newer_col, move.newer_row,king, kingCol, kingRow, -1, 1)||

                hitByKnight(move.newer_col, move.newer_row, king, kingCol, kingRow)||
                hitByPawn(move.newer_col, move.newer_row, king, kingCol, kingRow)||

                hitByKing( move.newer_col, move.newer_row, king , kingCol, kingCol );





    }

    private boolean hitByRook(int col, int row, Pieces King, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row) {
                break;
            }
            Pieces piece = board.getPieces(kingCol + (i * colVal), kingRow + (i * rowVal));
            if (piece != null && piece != board.selectedPieces) {

                if (!board.sameTeam(piece, King) && (piece.name.equals("Rook") || piece.name.equals("Queen"))) {
                    return true;


                }
                break;
            }
        }
        return false;

    }


    private boolean hitByBishop(int col, int row, Pieces king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if ((kingCol - (i * colVal)) == col && kingRow - (i * rowVal) == row) {
                break;
            }

            Pieces piece = board.getPieces( kingCol - (i * colVal),  kingRow - (i * rowVal));
            if (piece != null && piece != board.selectedPieces) {
                if (!board.sameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }

        return false;
    }

    private boolean hitByKnight(int col, int row, Pieces king, int kingCol, int kingRow) {
        return checkKnight(board.getPieces(kingCol - 1, kingRow - 2), king, col, row)||
                checkKnight(board.getPieces( kingCol + 1,  kingRow - 2), king, col, row)||
                checkKnight(board.getPieces( kingCol + 2,  kingRow - 1), king, col, row)||
                checkKnight(board.getPieces( kingCol + 2,  kingRow + 1), king, col, row)||
                checkKnight(board.getPieces(kingCol + 1, kingRow + 2), king, col, row)||
                checkKnight(board.getPieces( kingCol - 1,  kingRow + 2), king, col, row)||
                checkKnight(board.getPieces( kingCol - 2,  kingRow + 1), king, col, row)||
                checkKnight(board.getPieces( kingCol - 2, kingRow - 1), king, col, row);
    }

    private boolean checkKnight(Pieces p, Pieces k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("Knight") && (p.col == col && p.row == row);
    }
    private boolean hitByKing(int col, int row, Pieces king, int kingCol, int kingRow) {
        return
                checkKing(board.getPieces(kingCol - 1,  kingRow - 1), king)||
                        checkKing(board.getPieces( kingCol + 1,  kingRow - 1), king)||
                        checkKing(board.getPieces( kingCol - 1,  kingRow - 1), king)||
                        checkKing(board.getPieces( kingCol - 1,kingRow - 1), king)||
                        checkKing(board.getPieces( kingCol + 1,  kingRow + 1), king)||
                        checkKing(board.getPieces( kingCol - 1,  kingRow + 1), king)||
                        checkKing(board.getPieces( kingCol + 1,  kingRow + 1), king)||
                        checkKing(board.getPieces( kingCol + 1, kingRow + 1), king);
    }

    private boolean checkKing(Pieces p, Pieces k) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("King");
    }

    private boolean hitByPawn(int col, int row, Pieces king, int kingCol, int kingRow) {
        int colorVal = king.isWhite ? -1 : 1;
        return checkPawn(board.getPieces( kingCol + 1,  kingRow + colorVal), king, col, row)||
                checkPawn(board.getPieces( kingCol - 1, kingRow + colorVal), king, col, row);
    }

    private boolean checkPawn(Pieces p, Pieces k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("Pawn") && (p.col == col && p.row == row);
    }


    public boolean Checkmate(Pieces king){
        for(Pieces piece : board.piecesList){
            if (board.sameTeam(piece ,king)){
                board.selectedPieces = piece == king ? king : null ;
                for (int row = 0; row < board.rows; row++){
                    for (int col = 0; col < board.cols; col++) {
                        Move move = new Move(board, piece, col, row);
                        if ( board.isValidMove(move )){
                            return false ;
                        }
                    }
                }


            }
        }

        return true ;






}




}
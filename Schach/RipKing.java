package Schach;

import Pieces.Schachfiguren;

public class RipKing {

    Board board ;

    // Konstruktor, speichert das übergebene Spielfeld (Board)
    public RipKing(Board board){
        this.board = board;
    }

    // Prüft, ob der König durch einen gegnerischen Zug bedroht wird
    public boolean ripKing(Move move) {
        Schachfiguren king = board.ripKing(move.piece.isWhite); // findet den König des aktuellen Spielers
        assert king != null;
        int kingCol = king.col;
        int kingRow = king.row;

        // Wenn der König gerade gezogen wird, verwende seine neue Position
        if (board.selectedSchachfiguren != null && board.selectedSchachfiguren.name.equals("King")) {
            kingCol = move.newer_col;
            kingRow = move.newer_row;
        }

        // Prüft auf Bedrohung durch Türme, Läufer, Springer, Bauern und den gegnerischen König
        return hitByRook(move.newer_col, move.newer_row, king, kingCol, kingRow, 0, 1)||   // vertikal hoch
                hitByRook(move.newer_col, move.newer_row, king, kingCol, kingRow, 1, 0)||   // horizontal rechts
                hitByRook(move.newer_col, move.newer_row, king, kingCol, kingRow, 0, -1)||  // vertikal runter
                hitByRook(move.newer_col, move.newer_row, king, kingCol, kingRow, -1, 0)||  // horizontal links

                hitByBishop(move.newer_col, move.newer_row, king, kingCol, kingRow, -1, -1)|| // diagonal oben links
                hitByBishop(move.newer_col, move.newer_row, king, kingCol, kingRow, 1, -1)||  // diagonal oben rechts
                hitByBishop(move.newer_col, move.newer_row, king, kingCol, kingRow, 1, 1)||   // diagonal unten rechts
                hitByBishop(move.newer_col, move.newer_row,king, kingCol, kingRow, -1, 1)||   // diagonal unten links

                hitByKnight(move.newer_col, move.newer_row, king, kingCol, kingRow)||         // durch Springer
                hitByPawn(move.newer_col, move.newer_row, king, kingCol, kingRow)||           // durch Bauer

                hitByKing( move.newer_col, move.newer_row, king , kingCol, kingCol );         // durch gegnerischen König
    }

    // Prüft Bedrohung durch Turm oder Dame in geraden Linien
    private boolean hitByRook(int col, int row, Schachfiguren King, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row) {
                break;
            }
            Schachfiguren piece = board.getPieces(kingCol + (i * colVal), kingRow + (i * rowVal));
            if (piece != null && piece != board.selectedSchachfiguren) {
                if (!board.sameTeam(piece, King) && (piece.name.equals("Rook") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    // Prüft Bedrohung durch Läufer oder Dame in diagonalen Linien
    private boolean hitByBishop(int col, int row, Schachfiguren king, int kingCol, int kingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if ((kingCol - (i * colVal)) == col && kingRow - (i * rowVal) == row) {
                break;
            }
            Schachfiguren piece = board.getPieces( kingCol - (i * colVal),  kingRow - (i * rowVal));
            if (piece != null && piece != board.selectedSchachfiguren) {
                if (!board.sameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    // Prüft Bedrohung durch einen Springer
    private boolean hitByKnight(int col, int row, Schachfiguren king, int kingCol, int kingRow) {
        return checkKnight(board.getPieces(kingCol - 1, kingRow - 2), king, col, row)||
                checkKnight(board.getPieces( kingCol + 1,  kingRow - 2), king, col, row)||
                checkKnight(board.getPieces( kingCol + 2,  kingRow - 1), king, col, row)||
                checkKnight(board.getPieces( kingCol + 2,  kingRow + 1), king, col, row)||
                checkKnight(board.getPieces(kingCol + 1, kingRow + 2), king, col, row)||
                checkKnight(board.getPieces( kingCol - 1,  kingRow + 2), king, col, row)||
                checkKnight(board.getPieces( kingCol - 2,  kingRow + 1), king, col, row)||
                checkKnight(board.getPieces( kingCol - 2, kingRow - 1), king, col, row);
    }

    // Prüft, ob die angreifende Figur ein Knight ist,wenn ja dann Prüfung durch oberen Block
    private boolean checkKnight(Schachfiguren p, Schachfiguren k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("Knight") && (p.col == col && p.row == row);
    }

    // Prüft Bedrohung durch den gegnerischen König auf benachbarten Feldern
    private boolean hitByKing(int col, int row, Schachfiguren king, int kingCol, int kingRow) {
        return
                checkKing(board.getPieces(kingCol - 1,  kingRow - 1), king)||
                        checkKing(board.getPieces( kingCol + 1,  kingRow - 1), king)||
                        checkKing(board.getPieces( kingCol - 1,  kingRow - 1), king)||  // doppelt
                        checkKing(board.getPieces( kingCol - 1,kingRow - 1), king)||    // doppelt
                        checkKing(board.getPieces( kingCol + 1,  kingRow + 1), king)||
                        checkKing(board.getPieces( kingCol - 1,  kingRow + 1), king)||
                        checkKing(board.getPieces( kingCol + 1,  kingRow + 1), king)||  // doppelt
                        checkKing(board.getPieces( kingCol + 1, kingRow + 1), king);    // doppelt
    }

    // Prüft, ob die angreifende Figur ein King ist,wenn ja dann Prüfung durch oberen Block
    private boolean checkKing(Schachfiguren p, Schachfiguren k) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("King");
    }

    // Prüft Bedrohung durch einen gegnerischen Bauern diagonal
    private boolean hitByPawn(int col, int row, Schachfiguren king, int kingCol, int kingRow) {
        int colorVal = king.isWhite ? -1 : 1; // Weiße schlagen nach oben, Schwarze nach unten
        return checkPawn(board.getPieces( kingCol + 1,  kingRow + colorVal), king, col, row)||
                checkPawn(board.getPieces( kingCol - 1, kingRow + colorVal), king, col, row);
    }

    // Prüft, ob das angegebene Feld von einem Bauern bedroht wird
    private boolean checkPawn(Schachfiguren p, Schachfiguren k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p.name.equals("Pawn") && (p.col == col && p.row == row);
    }

    // Prüft, ob der König im Schachmatt ist – also ob es keinen legalen Zug mehr gibt
    public boolean Checkmate(Schachfiguren king){
        for(Schachfiguren piece : board.schachfigurenList){
            if (board.sameTeam(piece ,king)){
                board.selectedSchachfiguren = piece == king ? king : null ;
                for (int row = 0; row < board.rows; row++){
                    for (int col = 0; col < board.cols; col++) {
                        Move move = new Move(board, piece, col, row);
                        if ( board.isValidMove(move )){
                            return false ; // mindestens ein legaler Zug – kein Schachmatt
                        }
                    }
                }
            }
        }
        return true ; // kein legaler Zug – Schachmatt
    }
}

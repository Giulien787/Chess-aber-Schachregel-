package de.chemeker.Schach;


import de.chemeker.Piece.Pieces;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {
    Board board;
    public int[] mousepos;
    Board Board1;
    int dragOffsetX, dragOffsetY;


    public Input(Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;
/*
        Pieces piecesXY = board.getPieces(col, row);
        if (piecesXY != null) {
            board.selectedPieces = piecesXY;

 */


        Pieces p = board.getPieces(e.getX() / board.tileSize, e.getY() / board.tileSize);
        if (p != null) {
            board.selectedPieces = p;

            // Berechne den Klick-Offset relativ zur Figur
            dragOffsetX = e.getX() - p.posx;
            dragOffsetY = e.getY() - p.posy;
        }
        //}
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        /*
    }
        if (board.selectedPieces != null) {
            board.selectedPieces.posx = e.getX() - board.tileSize / 2;
            board.selectedPieces.posy = e.getY() - board.tileSize / 2;


            board.repaint();

         */


        if (board.selectedPieces != null) {
            board.selectedPieces.posx = e.getX() - dragOffsetX;
            board.selectedPieces.posy = e.getY() - dragOffsetY;

            board.repaint();
        }
        //}

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.tileSize; // Spalte basierend auf Mausposition berechnen
        int row = e.getY() / board.tileSize; // Zeile basierend auf Mausposition berechnen


        if (board.selectedPieces != null) { // Prüfen, ob eine Figur ausgewählt ist
            Move move = new Move(board, board.selectedPieces, row, col); // Erstelle ein neues Zug-Objekt

            if (board.isValidMove(move)) { // Prüfen, ob der Zug gültig ist
                board.makeMove(move); // Den Zug ausführen
            } else {
                // Falls ungültig, Figur an ursprüngliche Position zurücksetzen
                board.selectedPieces.posx = board.selectedPieces.col * board.tileSize;
                board.selectedPieces.posy = board.selectedPieces.row * board.tileSize;
            }
        }

        board.selectedPieces = null; // Auswahl aufheben
        board.repaint(); // Das Spielfeld neu zeichnen
    }






/*
    public Input(Board board) {
        this.board = board;
        mousepos = new int[2];
    }



    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        System.out.println("pressed mouse at: "+e.getX() +" "+ e.getY());
        mousepos[0] = e.getX();
        mousepos[1] = e.getY();
        board.repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }



    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }


 */
}

package Schach;


import Pieces.Schachfiguren;

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

        Schachfiguren p = board.getPieces(e.getX() / board.tileSize, e.getY() / board.tileSize);


        if (p != null && p.isWhite == board.isweiß_bewegt()) {
            board.selectedSchachfiguren = p;
            dragOffsetX = e.getX() - p.posx;
            dragOffsetY = e.getY() - p.posy;
        }

    }


    @Override
    public void mouseDragged(MouseEvent e) {

        if (board.selectedSchachfiguren != null) {
            board.selectedSchachfiguren.posx = e.getX() - dragOffsetX;
            board.selectedSchachfiguren.posy = e.getY() - dragOffsetY;

            board.repaint();
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.tileSize; // Spalte basierend auf Mausposition berechnen
        int row = e.getY() / board.tileSize; // Zeile basierend auf Mausposition berechnen


        if (board.selectedSchachfiguren != null) { // Prüfen, ob eine Figur ausgewählt ist
            Move move = new Move(board, board.selectedSchachfiguren, row, col); // Erstelle ein neues Zug-Objekt

            if (board.isValidMove(move)) { // Prüfen, ob der Zug gültig ist
                board.makeMove(move); // Den Zug ausführen
            } else {
                // Falls ungültig, Figur an ursprüngliche Position zurücksetzen
                board.selectedSchachfiguren.posx = board.selectedSchachfiguren.col * board.tileSize;
                board.selectedSchachfiguren.posy = board.selectedSchachfiguren.row * board.tileSize;
            }
        }

        board.selectedSchachfiguren = null; // Auswahl aufheben
        board.repaint(); // Das Spielfeld neu zeichnen
    }

}

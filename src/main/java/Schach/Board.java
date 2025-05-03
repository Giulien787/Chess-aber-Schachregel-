package Schach;


import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Board extends JPanel {
    public int tileSize = 110;
    public char[] Chararr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', ' '};



    public int cols;
    public int rows;
    public ArrayList<Pieces> piecesList;
    public Input input;
    public Pieces selectedPieces;
    Input input_1 = new Input(this);
    RipKing ripking = new RipKing(this);

    private boolean isweiß_bewegt = true;
    private boolean Checkmate = false;



    public Board() {
        this.cols = 8;
        this.rows = 8;
        this.input = new Input(this);
        this.addMouseListener(this.input);
        this.addMouseMotionListener(this.input);

        piecesList = new ArrayList<>();


        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));


        addpieces();

        this.setBackground(Color.LIGHT_GRAY);
    }



    public Pieces getPieces(int col, int row) {


        for (Pieces pieces : piecesList) {
            if (pieces.col == col && pieces.row == row) {


                return pieces;
            }
            // Geht alle Pieces.Pieces-Objekte in der Liste piecesList durch je nach Rotationsrunde ist piece anders beleget


        }
        return null; // Gibt null zurück, wenn kein gültiges Ergebnis vorhanden ist
    }


    private void promoted_pawn(Move move){


        Pieces zielFigur = getPieces(move.newer_col, move.newer_row);
        if (zielFigur != null) {
            capture(zielFigur);
        }
        piecesList.add(new Queen(this, move.newer_col, move.newer_row, move.piece.isWhite));
        capture(move.piece);

        repaint();
    }

    public void pawn(Move move) {
        int colorIndex = move.piece.isWhite ? 0 : 7;

        if (move.newer_row == colorIndex) {
            promoted_pawn(move); // neue Pieces.Queen rein, alter Bauer raus
            return;
        }

        move.piece.col = move.newer_col;
        move.piece.row = move.newer_row;
        move.piece.posx = move.newer_col * tileSize;
        move.piece.posy = move.newer_row * tileSize;
        move.piece.isFirstMove = false;

        capture(move.gefangen);
    }

    // Führt den Zug einer Figur aus
    public void makeMove(Move move) {
        capture(move.gefangen); // Falls eine gegnerische Figur geschlagen wird, entferne sie
        isweiß_bewegt = !isweiß_bewegt;
        if (move.piece instanceof Pawn) {
            pawn(move); // Sonderbehandlung für Bauern (wegen Umwandlung)
        } else {
            move.piece.col = move.newer_col;
            move.piece.row = move.newer_row;
            move.piece.posx = move.newer_col * tileSize;
            move.piece.posy = move.newer_row * tileSize;
            move.piece.isFirstMove = false;

            System.out.println(move.piece.posx);




            updatespielstand();
        }

    }

    public boolean isweiß_bewegt() {
        return isweiß_bewegt;
    }

    // Entfernt eine geschlagene Figur vom Spielfeld
    public void capture(Pieces pieces) {
        piecesList.remove(pieces);

        // Entfernt die geschlagene Figur aus der Liste
    }






    // Überprüft, ob ein Zug gültig ist
    public boolean isValidMove(Move move) {

        if (Checkmate){

            return  false;
        }


        if (!(move.piece.isWhite == isweiß_bewegt)){ // HIer ist der Fehler zur Spielerabfolge

            return  false;
        }

        if (sameTeam(move.piece, move.gefangen)) { // Prüft, ob das Ziel eine eigene Figur ist
            return false; // Falls ja, ist der Zug ungültig
        }
        if( ! move.piece.isValidmove(move.newer_col, move.newer_row)){

            return false;
        }


        if( move.piece.Blocked(move.newer_col, move.newer_row)){            // sind Figuren im Weg?

            return false;
        }


        if (ripking.ripKing(move)){
            System.out.println("Called and passed");

            return false;
        }


        return true; // Andernfalls ist der Zug gültig
    }

    // Prüft, ob zwei Figuren zum selben Team gehören
    public boolean sameTeam(Pieces p1, Pieces p2) {
        if (p1 == null || p2 == null) { // Falls eine der Figuren null ist, sind sie nicht im selben Team
            return false;
        }
        return p1.isWhite == p2.isWhite; // Vergleicht, ob beide Figuren die gleiche Farbe haben

    }

    Pieces ripKing(boolean isWhite ){

        for (Pieces piece: piecesList) {


            if (isWhite == piece.isWhite && piece.name.equals ("King")){

                return piece;
            }

        }



        return null;

    }






    public void addpieces() {
        //Black Pieces.Pieces
        piecesList.add(new Knight(this, 1, 0, false));

        piecesList.add(new Knight(this, 6, 0, false));

        piecesList.add(new Pawn(this, 0, 1, false));

        piecesList.add(new Pawn(this, 1, 1, false));

        piecesList.add(new Pawn(this, 2, 1, false));

        piecesList.add(new Pawn(this, 3, 1, false));

        piecesList.add(new Pawn(this, 4, 1, false));

        piecesList.add(new Pawn(this, 5, 1, false));

        piecesList.add(new Pawn(this, 6, 1, false));

        piecesList.add(new Pawn(this, 7, 1, false));

        piecesList.add(new King(this, 4, 0, false));

        piecesList.add(new Queen(this, 3, 0, false));

        piecesList.add(new Rook(this, 0, 0, false));

        piecesList.add(new Rook(this, 7, 0, false));

        piecesList.add(new Bishop(this, 2, 0, false));

        piecesList.add(new Bishop(this, 5, 0, false));


        //Black Pieces.Pieces


        piecesList.add(new Knight(this, 1, 7, true));

        piecesList.add(new Knight(this, 6, 7, true));

       piecesList.add(new Pawn(this, 0, 6, true));

        piecesList.add(new Pawn(this, 1, 6, true));

        piecesList.add(new Pawn(this, 2, 6, true));

        piecesList.add(new Pawn(this, 3, 6, true));

        piecesList.add(new Pawn(this, 4, 6, true));

        piecesList.add(new Pawn(this, 5, 6, true));

        piecesList.add(new Pawn(this, 6, 6, true));

        piecesList.add(new Pawn(this, 7, 6, true));



        piecesList.add(new Queen(this, 3, 7, true));

        piecesList.add(new Rook(this, 0, 7, true));

        piecesList.add(new Rook(this, 7, 7, true));

        piecesList.add(new Bishop(this, 2, 7, true));

        piecesList.add(new Bishop(this, 5, 7, true));



        piecesList.add(new King(this, 4, 7, true));
    }

    private void updatespielstand(){
        Pieces King = ripKing(isweiß_bewegt);
        if (ripking.Checkmate(King) ){
            if (ripking.ripKing(new Move(this, King, King.col, King.row))) {


                System.out.println(isweiß_bewegt ? "Schwarz gewinnt" : "Weiß gewinnt"); // der ? Opreator funktioniert wie ein verkürztes is statement (rüft ob isweiß_bewegt true oder false ist)
                int option = JOptionPane.showInternalConfirmDialog(null,true ? "Schwarz gewinnt Willst du nochmal spielen?" : "Weiß gewinnt Willst du nochmal spielen?","Chess",JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    System.out.println("Spiel startet neu!");
                    ChessMain.neustart();
                }
            }else {

                System.out.println("Patt(Gleichstand)");

            }
            Checkmate  = true ;
        } else if (insufficientMaterial( true) && insufficientMaterial( false )) {
            System.out.println("Insufficient Material!");
            Checkmate = true;
        }

    }

    private  boolean insufficientMaterial(boolean isWhite){
        ArrayList<String> names = piecesList.stream()
                .filter(p -> p.isWhite == isWhite)
                .map(p -> p.name)
                .collect(Collectors.toCollection(ArrayList :: new  ));
        if(names.contains("Queen") || names.contains("Rook") || names.contains("Pawn")){

            return  false;
        }
        return names.size() < 3;
    }

    //private void uptad
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        boolean black = true;
        //paint Board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {


                if ((r + c) % 2 == 0) {
                    graphics2D.setColor(new Color(139, 69, 19));
                } else {
                    graphics2D.setColor(Color.WHITE);


                }
                if (selectedPieces != null) {
                    if (isValidMove(new Move(this, selectedPieces, r, c)) && selectedPieces != null) { // Vorrausberechnung der valid Figurenbewegungen
                        graphics2D.setColor(new Color(46, 139, 87));
                    }
                }



//                x = c * tileSize;
//                if (black) {
//                    graphics2D.setColor(new Color(139,69,19));
//                } else {
//                    graphics2D.setColor(Color.WHITE);
//                }
//                black = !black;
//                if (c +1 == cols) {
//                    black = !black;
//                }
                graphics2D.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillRect(cols * tileSize, r * tileSize, tileSize, tileSize);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(String.valueOf(r + 1), cols * tileSize + (tileSize / 2), r * tileSize + (tileSize / 2));
            //ImageIcon image = new Pieces.Knight(this, 0, 0, false, 0, 0, "", 0).image;
            //graphics2D.drawImage();


        }

        for (int col = 0; col < cols + 1; col++) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillRect(col * tileSize, rows * tileSize, tileSize, tileSize);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(Chararr[col] + "", col * tileSize + (tileSize / 2), rows * tileSize + (tileSize / 2));
        }

        for (Pieces pieces : piecesList) {

            pieces.paint(graphics2D);


        }

     /*   if(input.mousepos != null) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillOval(input.mousepos[0] * tileSize , input.mousepos[1] * tileSize , tileSize / 2, tileSize /2);
        }
    */


    }
}
package Schach;


import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Board extends JPanel {
    // Größe eines Feldes auf dem Schachbrett in Pixel
    public int tileSize = 110;
    // Zeichen für die Spaltenbeschriftung (A–H)
    public char[] Chararr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', ' '};

    public int cols;
    public int rows;
    public ArrayList<Schachfiguren> schachfigurenList;
    public Input input;
    public Schachfiguren selectedSchachfiguren;
    public RipKing ripking = new RipKing(this);

    private boolean isweiß_bewegt = true; // true = Weiß ist am Zug, false = Schwarz
    private boolean Checkmate = false; // Gibt an, ob das Spiel vorbei ist

    public Board() {
        this.cols = 8;
        this.rows = 8;
        this.input = new Input(this);
        this.addMouseListener(this.input);
        this.addMouseMotionListener(this.input);

        schachfigurenList = new ArrayList<>(); // Liste aller Schachfiguren

        // Setzt die Größe des Panels entsprechend der Brettgröße
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));

        addpieces(); // Figuren auf das Brett setzen

        this.setBackground(Color.LIGHT_GRAY); // Hintergrundfarbe
    }

    // Gibt die Figur an der angegebenen Position zurück
    public Schachfiguren getPieces(int col, int row) {
        for (Schachfiguren schachfiguren : schachfigurenList) {
            if (schachfiguren.col == col && schachfiguren.row == row) {
                return schachfiguren;
            }
        }
        return null; // Keine Figur an der Position
    }

    // Behandelt die Umwandlung eines Bauern in eine Dame
    private void promoted_pawn(Move move) {
        Schachfiguren zielFigur = getPieces(move.newer_col, move.newer_row);
        if (zielFigur != null) {
            capture(zielFigur); // Entferne gegnerische Figur, falls vorhanden
        }
        schachfigurenList.add(new Queen(this, move.newer_col, move.newer_row, move.piece.isWhite)); // Neue Dame hinzufügen
        capture(move.piece); // Entferne den Bauern
        repaint(); // Darstellung aktualisieren
    }

    private void MoveKing(Move move) {

            if (Math.abs(move.piece.col - move.newer_col) == 2) {
                Schachfiguren rook;
                if (move.piece.col < move.newer_col) {
                    rook = getPieces(  7, move.piece.row);
                    rook. col = 5;
                } else {
                    rook = getPieces(  0, move.piece.row);
                    rook. col = 3;
                }
                rook.posx = rook.col * tileSize;
            }
    }

    // Führt einen Bauernzug aus (inkl. Umwandlung)
    public void pawn(Move move) {
        int colorIndex = move.piece.isWhite ? 0 : 7; // Zielreihe je nach Farbe (Weiß = 0, Schwarz = 7)

        if (move.newer_row == colorIndex) {
            promoted_pawn(move); // Umwandlung wenn Zielreihe erreicht
            return;
        }

        // Aktualisiere Position des Bauern
        move.piece.col = move.newer_col;
        move.piece.row = move.newer_row;
        move.piece.posx = move.newer_col * tileSize;
        move.piece.posy = move.newer_row * tileSize;
        move.piece.isFirstMove = false;

        capture(move.gefangen); // geschlagene Figur entfernen
    }

    // Führt den Zug einer Figur aus
    public void makeMove(Move move) {
        capture(move.gefangen); // Figur schlagen, falls vorhanden
        isweiß_bewegt = !isweiß_bewegt; // Spielerwechsel
        if (move.piece instanceof Pawn) {
            pawn(move); // Bauernzüge gesondert behandeln
        } else if (move.piece.name.equals("King")) {
            MoveKing(move); }
            // Position aktualisieren für andere Figuren
            move.piece.col = move.newer_col;
            move.piece.row = move.newer_row;
            move.piece.posx = move.newer_col * tileSize;
            move.piece.posy = move.newer_row * tileSize;

            move.piece.isFirstMove = false;

            System.out.println(move.piece.posx);

            updatespielstand(); // Spielstand prüfen
        }


    // Gibt zurück, ob Weiß am Zug ist
    public boolean isweiß_bewegt() {
        return isweiß_bewegt;
    }

    // Entfernt eine geschlagene Figur vom Spielfeld
    public void capture(Schachfiguren schachfiguren) {
        schachfigurenList.remove(schachfiguren);
    }

    // Überprüft, ob ein Zug gültig ist
    public boolean isValidMove(Move move) {
        if (Checkmate){
            return  false;
        }

        // Falscher Spieler am Zug
        if (!(move.piece.isWhite == isweiß_bewegt)){
            return  false;
        }

        // Zielposition durch eigene Figur besetzt?
        if (sameTeam(move.piece, move.gefangen)) {
            return false;
        }

        // Ist der Zug laut Figur gültig?
        if(!move.piece.isValidmove(move.newer_col, move.newer_row)){
            return false;
        }

        // Ist der Zug durch andere Figuren blockiert?
        if(move.piece.Blocked(move.newer_col, move.newer_row)){
            return false;
        }

        // Führt der Zug zu eigenem Schach?
        if (ripking.ripKing(move)){
            System.out.println();
            return false;
        }

        return true;
    }

    // Prüft, ob zwei Figuren im selben Team sind
    public boolean sameTeam(Schachfiguren p1, Schachfiguren p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }

    // Gibt den König der angegebenen Farbe zurück
    // hat mit der Erkennung von Schach zu tun
    Schachfiguren ripKing(boolean isWhite ){
        for (Schachfiguren piece: schachfigurenList) {
            if (isWhite == piece.isWhite && piece.name.equals ("King")){
                return piece;
            }
        }
        return null;
    }

    // Setzt alle Figuren zu Beginn des Spiels
    public void addpieces() {
        // Schwarze Figuren
        schachfigurenList.add(new Knight(this, 1, 0, false));
        schachfigurenList.add(new Knight(this, 6, 0, false));
        schachfigurenList.add(new Pawn(this, 0, 1, false));
        schachfigurenList.add(new Pawn(this, 1, 1, false));
        schachfigurenList.add(new Pawn(this, 2, 1, false));
        schachfigurenList.add(new Pawn(this, 3, 1, false));
        schachfigurenList.add(new Pawn(this, 4, 1, false));
        schachfigurenList.add(new Pawn(this, 5, 1, false));
        schachfigurenList.add(new Pawn(this, 6, 1, false));
        schachfigurenList.add(new Pawn(this, 7, 1, false));
        schachfigurenList.add(new King(this, 4, 0, false));
        schachfigurenList.add(new Queen(this, 3, 0, false));
        schachfigurenList.add(new Rook(this, 0, 0, false));
        schachfigurenList.add(new Rook(this, 7, 0, false));
        schachfigurenList.add(new Bishop(this, 2, 0, false));
        schachfigurenList.add(new Bishop(this, 5, 0, false));

        // Weiße Figuren
        schachfigurenList.add(new Knight(this, 1, 7, true));
        schachfigurenList.add(new Knight(this, 6, 7, true));
        schachfigurenList.add(new Pawn(this, 0, 6, true));
        schachfigurenList.add(new Pawn(this, 1, 6, true));
        schachfigurenList.add(new Pawn(this, 2, 6, true));
        schachfigurenList.add(new Pawn(this, 3, 6, true));
        schachfigurenList.add(new Pawn(this, 4, 6, true));
        schachfigurenList.add(new Pawn(this, 5, 6, true));
        schachfigurenList.add(new Pawn(this, 6, 6, true));
        schachfigurenList.add(new Pawn(this, 7, 6, true));
        schachfigurenList.add(new Queen(this, 3, 7, true));
        schachfigurenList.add(new Rook(this, 0, 7, true));
        schachfigurenList.add(new Rook(this, 7, 7, true));
        schachfigurenList.add(new Bishop(this, 2, 7, true));
        schachfigurenList.add(new Bishop(this, 5, 7, true));
        schachfigurenList.add(new King(this, 4, 7, true));
    }

    // Überprüft, ob das Spiel beendet ist (Schachmatt oder Patt)
    private void updatespielstand(){
        Schachfiguren King = ripKing(isweiß_bewegt);
        if (ripking.Checkmate(King) ){
            if (ripking.ripKing(new Move(this, King, King.col, King.row))) {
                System.out.println(isweiß_bewegt ? "Schwarz gewinnt" : "Weiß gewinnt");
                int option = JOptionPane.showInternalConfirmDialog(null,true ? "Schwarz gewinnt Willst du nochmal spielen?" : "Weiß gewinnt Willst du nochmal spielen?","Chess",JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    System.out.println("Spiel startet neu!");
                    ChessMain.neustart();
                }
            } else {
                System.out.println("Patt(Gleichstand)");
            }
            Checkmate  = true ;
        } else if (insufficientMaterial( true) && insufficientMaterial( false )) {
            System.out.println("Insufficient Material!");
            Checkmate = true;
        }
    }

    // Prüft auf zu wenig Material für ein Matt
    // ist eine Absicherung aus dem Internet
    private  boolean insufficientMaterial(boolean isWhite){
        ArrayList<String> names = schachfigurenList.stream()
                .filter(p -> p.isWhite == isWhite)
                .map(p -> p.name)
                .collect(Collectors.toCollection(ArrayList :: new));
        if(names.contains("Queen") || names.contains("Rook") || names.contains("Pawn")){
            return  false;
        }
        return names.size() < 3;
    }

    // Zeichnet das Spielbrett und alle Figuren
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        boolean black = true;

        // Zeichnet das Schachbrett (8x8 Felder)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r + c) % 2 == 0) {
                    graphics2D.setColor(new Color(139, 69, 19)); // braune Felder
                } else {
                    graphics2D.setColor(Color.WHITE); // weiße Felder
                }

                // Gültige Zielposition grün markieren
                if (selectedSchachfiguren != null) {
                    if (isValidMove(new Move(this, selectedSchachfiguren, r, c)) && selectedSchachfiguren != null) {
                        graphics2D.setColor(new Color(46, 139, 87));
                    }
                }

                graphics2D.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillRect(cols * tileSize, r * tileSize, tileSize, tileSize);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(String.valueOf(r + 1), cols * tileSize + (tileSize / 2), r * tileSize + (tileSize / 2));
        }

        // Zeichnet die Buchstaben A–H unten am Rand
        for (int col = 0; col < cols + 1; col++) {
            graphics2D.setColor(Color.GRAY);
            graphics2D.fillRect(col * tileSize, rows * tileSize, tileSize, tileSize);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(Chararr[col] + "", col * tileSize + (tileSize / 2), rows * tileSize + (tileSize / 2));
        }

        // Zeichnet alle Figuren
        for (Schachfiguren schachfiguren : schachfigurenList) {
            schachfiguren.paint(graphics2D);
        }
    }
}

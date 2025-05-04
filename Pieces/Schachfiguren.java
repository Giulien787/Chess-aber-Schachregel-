package Pieces;

import Schach.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public abstract class Schachfiguren {

    // Logische Position auf dem Schachbrett (Spalte und Zeile)
    public int col, row;

    // Grafische Position (Pixelkoordinaten für das Zeichnen)
    public int posx, posy;

    // Farbe der Figur: true = Weiß, false = Schwarz
    public boolean isWhite;

    // Name der Figur (z.B. "Bishop", "Pawn", ...)
    public String name;

    // Wert der Figur (z.B. für Bewertung im Schachalgorithmus)
    public int value;

    // Wird beim ersten Zug benötigt (z.B. Bauer darf zwei Felder ziehen)
    public boolean isFirstMove = true;

    // Bildquelle: gesamtes Sprite-Sheet mit allen Figuren
    BufferedImage Sheet;

    // Skalierung eines einzelnen Figurensprites aus dem Sheet
    protected int sheetscale;

    // Einzelnes Bild dieser Figur
    Image sprite;

    // Referenz auf das Schachbrett
    Board board;

    // Initialisierungsblock zum Laden des Bildes (wird beim Erzeugen der Instanz ausgeführt)
    {
        try {
            // Lädt das gesamte Bild aus der Datei "pieces.png"
            Sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("pieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Konstruktor – erhält das Spielfeld als Referenz.
     *
     * board Schachbrett, auf dem die Figur platziert ist
     */
    public Schachfiguren(Board board) {
        this.board = board;

        // Breite/Höhe eines einzelnen Sprite-Elements im Sheet berechnen
        this.sheetscale = Sheet.getWidth() / 6; // 6 Figuren pro Reihe im Bild
    }

    /*
     * Prüft, ob ein Zug für diese Figur gültig ist.
     * Muss von Unterklassen überschrieben werden.
     * col Zielspalte
     * row Zielzeile
     * return true, wenn der Zug möglich ist (default: immer true)
     */
    public boolean isValidmove(int col, int row) {
        return true;
    }

    /*
     * Prüft, ob die Figur auf dem Weg blockiert ist.
     * Wird in den Unterklassen wie Läufer, Turm etc. sinnvoll überschrieben.
     *
     * col Zielspalte
     * row Zielzeile
     * return true, wenn der Weg blockiert ist (default: nie blockiert)
     */
    public boolean Blocked(int col, int row) {
        return false;
    }

    /*
     * Zeichnet das Bild (Sprite) der Figur auf das Spielfeld.
     *
     * g2D Grafik-Kontext, in den das Bild gezeichnet wird
     */
    public void paint(Graphics2D g2D) {
        g2D.drawImage(sprite, posx, posy, null);
    }
}

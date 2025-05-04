package Schach;

import javax.swing.*;
import java.awt.*;

public class ChessMain {

    static JFrame frame; // Hauptfenster des Spiels

    public static void main(String[] args) {

        // Startet das Spiel
        neustart();
    }

    public static void neustart (){

        // Falls bereits ein Fenster existiert, wird es geschlossen
        if (frame != null) {
            frame.setVisible(false); // Blendet das alte Fenster aus
            frame = null;            // Setzt die Referenz auf null, um Ressourcen freizugeben
        }

        // Erstellt ein neues Fenster für das Spiel
        frame = new JFrame("Chess"); // Titel des Fensters ist "Chess"
        frame.setLayout(new GridLayout()); // Legt das Layout fest (einfaches Gitter)
        frame.setMinimumSize(new Dimension(1000, 1000)); // Minimale Fenstergröße 1000x1000 Pixel
        frame.setResizable(false); // Fenstergröße darf nicht verändert werden
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Beendet das Programm beim Schließen

        // Fügt das Schachbrett-Panel dem Fenster hinzu
        frame.add(new Board());
        frame.setVisible(true); // Macht das Fenster sichtbar
    }
}

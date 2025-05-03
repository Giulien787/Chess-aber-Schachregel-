package de.chemeker.Schach;

import javax.swing.*;
import java.awt.*;

public class ChessMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setLayout(new GridLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setResizable(false);



        frame.add(new Board());
        frame.setVisible(true);
    }
}
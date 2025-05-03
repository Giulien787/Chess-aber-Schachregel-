package Schach;

import javax.swing.*;
import java.awt.*;

public class ChessMain {

    static JFrame frame;


    public static void main(String[] args) {


        neustart();
    }
    public static void neustart (){


        if (frame != null) {
            frame.setVisible(false);
            frame = null;
        }

        frame = new JFrame("Chess");
        frame.setLayout(new GridLayout());
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(new Board());
        frame.setVisible(true);
    }
    }



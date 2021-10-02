package ru.vsu.cs.Lukashev;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private MainPanel mainPanel;

    private boolean night=false;
    private boolean drive=false;


    public MainFrame()
    {
        mainPanel = new MainPanel (night, drive );
        this.add(mainPanel);
        this.setSize(1200, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}

package com.tictactoe;
import java.io.IOException;

import com.formdev.flatlaf.themes.*;
public class Main {
    private static Game g;
    public static void main(String[] args) throws IOException {
        FlatMacLightLaf.setup();
        Main.g = new Game();
        g.setVisible(true);
    }
}
import javax.swing.*;
import game.*;
import logic.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameState state = new GameState();
            GamePanel panel = new GamePanel(state);
            TurnManager tm = new TurnManager(state);
            InputHandler ih = new InputHandler(panel, state, tm);
            ih.bindKeys();

            JFrame f = new JFrame("2D Tank Battle — 8x8");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            f.setContentPane(panel);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}

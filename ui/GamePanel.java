package ui;

import javax.swing.*;
import java.awt.*;
import game.*;

public class GamePanel extends JPanel {
    private final GameState state;
    private static final int TILE = 64;
    private static final int HUD = 80;

    public GamePanel(GameState state){
        this.state = state;
        setPreferredSize(new Dimension(GameState.COLS*TILE, GameState.ROWS*TILE + HUD));
        setBackground(Colors.BG);
    }

    @Override protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Grid
        for (int y=0; y<GameState.ROWS; y++){
            for (int x=0; x<GameState.COLS; x++){
                g2.setColor(((x+y)&1)==0 ? Colors.GRID_A : Colors.GRID_B);
                g2.fillRect(x*TILE, y*TILE, TILE, TILE);
            }
        }

        // Tanks
        for (Tank t : state.getTanks()){
            if (!t.isAlive()) continue;
            int px = t.getX()*TILE, py = t.getY()*TILE;

            g2.setColor(t.getOwner()==PlayerId.P1 ? Colors.P1 : Colors.P2);
            g2.fillRoundRect(px+12, py+12, TILE-24, TILE-24, 16,16);

            // turret/cannon line
            int cx = px + TILE/2, cy = py + TILE/2;
            int ex = cx + t.getFacing().dx * 26;
            int ey = cy + t.getFacing().dy * 26;
            g2.setStroke(new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(cx, cy, ex, ey);

            // HP bar
            int w = TILE-24, h = 8;
            int cur = (int)Math.round((t.getHp()/(double)t.getMaxHp()) * w);
            g2.setColor(Colors.BAR_BG); g2.fillRoundRect(px+12, py+TILE-14, w, h, 8,8);
            g2.setColor(Colors.HP); g2.fillRoundRect(px+12, py+TILE-14, cur, h, 8,8);
        }

        // HUD
        int y0 = GameState.ROWS*TILE + 20;
        g2.setColor(Color.WHITE);
        g2.drawString("Turn: " + (state.getTurn()==PlayerId.P1 ? "Player 1" : "Player 2"), 12, y0);
        g2.drawString("P1 Ammo (1/2/3): " + state.getAmmoChoice(PlayerId.P1), 12, y0+20);
        g2.drawString("P2 Ammo (7/8/9): " + state.getAmmoChoice(PlayerId.P2), 220, y0+20);
        g2.drawString("WASD/Space | Arrows/Enter | R reset", 12, y0+40);
    }
}
package ui;

import javax.swing.*;
import java.awt.event.*;
import game.*;
import logic.*;
import java.awt.event.KeyEvent;

public class InputHandler {
    private final GamePanel panel;
    private final GameState state;
    private final TurnManager tm;

    public InputHandler(GamePanel panel, GameState state, TurnManager tm){
        this.panel = panel; this.state = state; this.tm = tm;
    }

    public void bindKeys(){
        InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = panel.getActionMap();

        // P1 movement
        mapMove(im, am, KeyStroke.getKeyStroke('W'), PlayerId.P1, Direction.UP);
        mapMove(im, am, KeyStroke.getKeyStroke('A'), PlayerId.P1, Direction.LEFT);
        mapMove(im, am, KeyStroke.getKeyStroke('S'), PlayerId.P1, Direction.DOWN);
        mapMove(im, am, KeyStroke.getKeyStroke('D'), PlayerId.P1, Direction.RIGHT);
        // P2 movement
        mapMove(im, am, KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), PlayerId.P2, Direction.UP);
        mapMove(im, am, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), PlayerId.P2, Direction.LEFT);
        mapMove(im, am, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), PlayerId.P2, Direction.DOWN);
        mapMove(im, am, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), PlayerId.P2, Direction.RIGHT);

        // Fire
        bind(im, am, "P1_FIRE", KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0), () -> fire(PlayerId.P1));
        bind(im, am, "P2_FIRE", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), () -> fire(PlayerId.P2));

        // Ammo select
        bind(im, am, "P1_STD", KeyStroke.getKeyStroke('1'), () -> setAmmo(PlayerId.P1, Ammo.STANDARD));
        bind(im, am, "P1_AP",  KeyStroke.getKeyStroke('2'), () -> setAmmo(PlayerId.P1, Ammo.AP));
        bind(im, am, "P1_HE",  KeyStroke.getKeyStroke('3'), () -> setAmmo(PlayerId.P1, Ammo.HE));
        bind(im, am, "P2_STD", KeyStroke.getKeyStroke('7'), () -> setAmmo(PlayerId.P2, Ammo.STANDARD));
        bind(im, am, "P2_AP",  KeyStroke.getKeyStroke('8'), () -> setAmmo(PlayerId.P2, Ammo.AP));
        bind(im, am, "P2_HE",  KeyStroke.getKeyStroke('9'), () -> setAmmo(PlayerId.P2, Ammo.HE));

        // Reset
        bind(im, am, "RESET", KeyStroke.getKeyStroke('R'), this::reset);
    }

    private void mapMove(InputMap im, ActionMap am, KeyStroke ks, PlayerId p, Direction d){
        String name = p + "_MOVE_" + d;
        im.put(ks, name);
        am.put(name, new AbstractAction(){
            @Override public void actionPerformed(ActionEvent e){
                if (!tm.canAct(p)) return;
                Tank t = state.getTank(p);
                if (t != null) {
                    t.move(d, state); // <-- now defined on Tank
                    tm.endAction();
                    panel.repaint();
                }
            }
        });
    }

    private void fire(PlayerId p){
        if (!tm.canAct(p)) return;
        Tank shooter = state.getTank(p);
        CombatSystem.fireProjectile(shooter, state);
        tm.endAction();
        panel.repaint();
    }

    private void setAmmo(PlayerId p, Ammo a){
        state.setAmmoChoice(p, a);
        panel.repaint();
    }

    private void reset(){
        state.resetGame();
        panel.repaint();
    }

    private void bind(InputMap im, ActionMap am, String name, KeyStroke ks, Runnable action){
        im.put(ks, name);
        am.put(name, new AbstractAction(){ @Override public void actionPerformed(ActionEvent e){ action.run(); }});
    }
}

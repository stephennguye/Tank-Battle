package game;

import java.util.*;

public class GameState {
    public static final int COLS = 8, ROWS = 8;

    private final List<Tank> tanks = new ArrayList<>();
    private final Board board = new Board(COLS, ROWS);

    private PlayerId turn = PlayerId.P1;
    private Ammo p1Ammo = Ammo.STANDARD;
    private Ammo p2Ammo = Ammo.STANDARD;
    private boolean awaitingAction = true;

    public GameState(){ resetGame(); }

    public void resetGame(){
        tanks.clear();
        // positions similar to the original single-file example
        tanks.add(new Tank(PlayerId.P1, 1, 6, 20, 3, 7));
        tanks.add(new Tank(PlayerId.P2, 6, 1, 20, 3, 7));
        turn = PlayerId.P1;
        p1Ammo = Ammo.STANDARD;
        p2Ammo = Ammo.STANDARD;
        awaitingAction = true;
    }

    public Board getBoard(){ return board; }
    public List<Tank> getTanks(){ return tanks; }
    public Optional<Tank> tankAt(int x, int y){
        return tanks.stream().filter(t -> t.isAlive() && t.getX()==x && t.getY()==y).findFirst();
    }

    /** First alive tank of a player (supports multi-tank later). */
    public Tank getTank(PlayerId p){
        for (Tank t : tanks) if (t.getOwner()==p && t.isAlive()) return t;
        return null;
    }

    public PlayerId getTurn(){ return turn; }
    public boolean isAwaitingAction(){ return awaitingAction; }
    public void setAwaitingAction(boolean v){ awaitingAction = v; }

    public void nextTurn(){ turn = (turn==PlayerId.P1) ? PlayerId.P2 : PlayerId.P1; awaitingAction = true; }

    public boolean anyAlive(PlayerId p){
        return tanks.stream().anyMatch(t -> t.getOwner()==p && t.isAlive());
    }

    // Ammo helpers
    public Ammo getCurrentAmmo(){ return (turn==PlayerId.P1) ? p1Ammo : p2Ammo; }
    public void setCurrentAmmo(Ammo a){ if (turn==PlayerId.P1) p1Ammo=a; else p2Ammo=a; }
    public void setAmmoChoice(PlayerId p, Ammo a){ if (p==PlayerId.P1) p1Ammo=a; else p2Ammo=a; }
    public Ammo getAmmoChoice(PlayerId p){ return (p==PlayerId.P1) ? p1Ammo : p2Ammo; }
}

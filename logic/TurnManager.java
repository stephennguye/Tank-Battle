package logic;

import game.*;


public class TurnManager {
    private final GameState state;

    public TurnManager(GameState state){ this.state = state; }

    public boolean canAct(PlayerId p){ return state.getTurn()==p && state.isAwaitingAction(); }

    public void endAction(){
        state.setAwaitingAction(false);
        // stop if game over
        if (!state.anyAlive(PlayerId.P1) || !state.anyAlive(PlayerId.P2)) return;
        state.nextTurn();
    }
}
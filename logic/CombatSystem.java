package logic;
import game.*;
import java.util.Optional;

public class CombatSystem {
    public static void fireProjectile(Tank shooter, GameState state){
        if (shooter == null || !shooter.isAlive()) return;

        Ammo ammo = state.getCurrentAmmo();
        Direction d = shooter.getFacing();
        int dx = d.dx, dy = d.dy;

        int x = shooter.getX() + dx;
        int y = shooter.getY() + dy;

        int impactX = -1, impactY = -1;
        Tank hit = null;

        while (state.getBoard().isInside(x, y)) {
            Optional<Tank> maybe = state.tankAt(x, y);
            if (maybe.isPresent()) { hit = maybe.get(); impactX = x; impactY = y; break; }
            impactX = x; impactY = y; // last empty traveled
            x += dx; y += dy;
        }

        if (ammo == Ammo.HE && impactX >= 0) {
            for (int iy = impactY-1; iy <= impactY+1; iy++) {
                for (int ix = impactX-1; ix <= impactX+1; ix++) {
                    if (!state.getBoard().isInside(ix, iy)) continue;
                    state.tankAt(ix, iy).ifPresent(t -> apply(shooter, t, ammo));
                }
            }
        } else if (hit != null) {
            apply(shooter, hit, ammo);
        }
    }

    private static void apply(Tank shooter, Tank target, Ammo ammo){
        boolean ignoreArmor = (ammo == Ammo.AP);
        int raw = shooter.getDamage();
        target.applyDamage(raw, ignoreArmor);
    }
}

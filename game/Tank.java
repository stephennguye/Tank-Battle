package game;

public class Tank {
    private final PlayerId owner;
    private int x, y;
    private int hp, maxHp;
    private int armor;
    private int damage;
    private Direction facing = Direction.UP;

    public Tank(PlayerId owner, int x, int y, int hp, int armor, int damage){
        this.owner = owner;
        this.x = x; this.y = y;
        this.hp = hp; this.maxHp = hp;
        this.armor = armor; this.damage = damage;
    }

    public PlayerId getOwner(){ return owner; }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getHp(){ return hp; }
    public int getMaxHp(){ return maxHp; }
    public int getArmor(){ return armor; }
    public int getDamage(){ return damage; }
    public Direction getFacing(){ return facing; }
    public void setFacing(Direction d){ facing = d; }

    public boolean isAlive(){ return hp > 0; }

    public void applyDamage(int raw, boolean ignoreArmor){
        int taken = ignoreArmor ? raw : Math.max(0, raw - armor);
        hp = Math.max(0, hp - taken);
    }

    void setPos(int nx, int ny){ x = nx; y = ny; }

    /**
     * Try to move the tank one tile in the given direction.
     * - sets facing to dir
     * - returns true if the tank moved; false otherwise.
     *
     * Uses GameState to check board bounds and occupancy.
     */
    public boolean move(Direction dir, GameState state){
        if (!isAlive()) return false;
        setFacing(dir);
        int nx = x + dir.dx;
        int ny = y + dir.dy;

        // bounds
        if (!state.getBoard().isInside(nx, ny)) return false;

        // blocked by other tank
        if (state.tankAt(nx, ny).isPresent()) return false;

        setPos(nx, ny);
        return true;
    }
}

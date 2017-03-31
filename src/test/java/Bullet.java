public class Bullet {
    String shooter;
    int count = 0;

    public Bullet(String shooter, int count) {
        this.shooter = shooter;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Bullet(" + count + ')';
    }
}

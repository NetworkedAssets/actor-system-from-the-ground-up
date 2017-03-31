import org.na.actor.system.Actor;
import org.na.actor.system.ActorRef;

public class NeoActor implements Actor {

    @Override
    public void receive(ActorRef self, ActorRef sender, Object message) {
        if (message instanceof Bullet) {
            Bullet b = (Bullet) message;
            System.out.println("Pong " + b.shooter + "'s bullet " + b.count);
            sender.tell(self, new Bullet(b.shooter, b.count + 1));
        }
    }
}

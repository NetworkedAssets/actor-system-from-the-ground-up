import org.na.actor.system.Actor;
import org.na.actor.system.ActorRef;

import java.util.concurrent.ThreadLocalRandom;

public class SmithActor implements Actor {
    String name;
    ActorRef neo;
    ActorRef initiator;

    public SmithActor(String name, ActorRef neo) {
        this.name = name;
        this.neo = neo;
    }

    @Override
    public void receive(ActorRef self, ActorRef sender, Object message) {
        if(message instanceof StartShooting){
            initiator = sender;
            System.out.println("Start");
            neo.tell(self, new Bullet(name, 0));
        } else if(message instanceof Bullet){
            if(ThreadLocalRandom.current().nextInt(50) == 0){
                initiator.tell(self, message);
            }else{
                System.out.println("Ping");
                sender.tell(self, message);
            }
        }
    }
}

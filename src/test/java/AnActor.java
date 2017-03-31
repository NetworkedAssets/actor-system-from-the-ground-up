import org.na.actor.system.Actor;
import org.na.actor.system.ActorRef;

public class AnActor implements Actor {

    int a = 0;

    public void receive(ActorRef self, ActorRef sender, Object message) {
        a++;
        System.out.println(message);
        sender.tell(self, message.toString() + "2");
    }

}

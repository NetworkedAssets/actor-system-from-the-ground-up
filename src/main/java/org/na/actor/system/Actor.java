package org.na.actor.system;

public interface Actor {
    void receive(ActorRef self, ActorRef sender, Object message);
}

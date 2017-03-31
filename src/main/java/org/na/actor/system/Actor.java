package org.na.actor.system;

public interface Actor {
    public void receive(ActorRef self, ActorRef sender, Object message);
}

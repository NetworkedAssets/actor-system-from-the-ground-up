package org.na.actor.system;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActorSystem {

    ExecutorService es = Executors.newFixedThreadPool(8);

    public ActorRef create(Actor actor){
        return new ActorRef(actor, this);
    }

}

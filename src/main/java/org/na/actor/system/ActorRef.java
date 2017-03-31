package org.na.actor.system;

import java.util.concurrent.CompletableFuture;

public class ActorRef {

    Actor actor;
    ActorSystem actorSystem;
    Mailbox mailbox = new Mailbox(this);

    public ActorRef(Actor actor, ActorSystem actorSystem) {
        this.actor = actor;
        this.actorSystem = actorSystem;
    }

    public void tell(Object message) {
        tell(actorSystem.create(new NullActor()), message);
    }

    static class NullActor implements Actor{
        @Override
        public void receive(ActorRef self, ActorRef sender, Object message) {}
    }

    public CompletableFuture<Object> ask(Object message) {
        AskActor askActor = new AskActor();
        CompletableFuture f = askActor.future;
        tell(actorSystem.create(askActor), message);
        return f;
    }

    static class AskActor implements Actor {

        CompletableFuture<Object> future = new CompletableFuture<>();

        @Override
        public void receive(ActorRef self, ActorRef sender, Object message) {
            if (!future.isDone()) future.complete(message);
        }
    }

    public void tell(ActorRef sender, Object message) {
        mailbox.put(sender, message);
    }
}

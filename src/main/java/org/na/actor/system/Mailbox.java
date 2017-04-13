package org.na.actor.system;

import com.google.gag.annotation.remark.ThisWouldBeOneLineIn;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

class Mailbox {

    ActorRef actorRef;
    ConcurrentLinkedQueue<Mail> queue = new ConcurrentLinkedQueue<>();
    AtomicBoolean scheduled = new AtomicBoolean(false);

    Mailbox(ActorRef actorRef) {
        this.actorRef = actorRef;
    }

    void put(ActorRef sender, Object message) {
        queue.offer(new Mail(sender, message));
        scheduleProcessing();
    }

    private void scheduleProcessing() {
        boolean shouldSchedule = scheduled.compareAndSet(false, true);
        if (shouldSchedule) {
            actorRef.actorSystem.es.submit(this::process);
        }
    }

    private void process() {
        Mail m = queue.poll();
        actorRef.actor.receive(actorRef, m.sender, m.message);
        scheduled.set(false);
        if (!queue.isEmpty()) {
            scheduleProcessing();
        }
    }

    @ThisWouldBeOneLineIn(language = "Scala || Kotlin", toWit = "case class Mail(sender: ActorRef, message: Object)")
    private static class Mail {
        ActorRef sender;
        Object message;

        Mail(ActorRef sender, Object message) {
            this.sender = sender;
            this.message = message;
        }
    }
}

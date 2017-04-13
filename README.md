An aesthetic example of an actor system implementation inspired by Akka.
It was written during a knowledge exchange live coding session at NetworkedAssets.
Here's a blog post about it: [Actor System from the Ground Up - Live Coding Session](https://www.networkedassets.com/actor-system-from-the-ground-up-live-coding-session/)

# Installation

It's an sbt based project written in Java. Just check how to import an sbt project in your favourite IDE.

# Usage
The main way of using it is to play with the code to understand how an actor system can be implemented in Java.
Good starting points are:

* NeoTest - a simple example where a NeoActor is receiving Bullet messages from a hundred of SmithActors and deflecting them back to Smiths which deflect them back to Neo again (kind of a ping-pong).
* ActorSystem - that's where the actor system implementation starts
* Mailbox - most magic is here

## What it does
* Let's you create actors which can:
    * receive messages
    * send messages
    * alter it's state
* An actor is guaranteed that it won't process two messages in parallel*, therefore it is free to alter it's state without use of kind of synchronization mechanisms (synchronized, locks, volatile, etc)

*-in fact it is guaranteed that the end of processing of one message happens before the start of processing of next message

## Available ways of sending a message
* tell (send) with known sender - useful when sending message from actor to actor
* tell (send) with unknown sender - useful when sending a message from outside of the actor system. It gives no way of delivering a response. 
* ask - returns a CompletableFuture, which may contain a response in the future.

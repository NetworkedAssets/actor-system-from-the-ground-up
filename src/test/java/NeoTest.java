import org.junit.Test;
import org.na.actor.system.ActorRef;
import org.na.actor.system.ActorSystem;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NeoTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ActorSystem actorSystem = new ActorSystem();
        ActorRef neo = actorSystem.create(new NeoActor());

        CompletableFuture.allOf(
                IntStream.range(0, 100).mapToObj(i -> {
                    ActorRef smith = actorSystem.create(new SmithActor("Smith" + i, neo));
                    return smith.ask(new StartShooting());
                }).collect(Collectors.toList()).toArray(new CompletableFuture[0])
        ).get();

//        CompletableFuture<Object> response = smith.ask(new StartShooting());
//        System.out.println(response.get());
    }
}

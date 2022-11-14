package pe.com.reactive.sec02Flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import pe.com.reactive.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec07Subscription {

    public static void main(String[] args) {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1, 10)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received subscription :: " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext :: " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("ERROR:: " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(".:: onComplete ::.");
                    }
                });

        Util.sleepSeconds(3);
        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        System.out.println("Going to cancel ...");
        atomicReference.get().cancel(); //Luego del cancel no se podrán obtener más items
        Util.sleepSeconds(3);
        atomicReference.get().request(4);

    }
}

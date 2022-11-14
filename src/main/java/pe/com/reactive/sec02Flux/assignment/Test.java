package pe.com.reactive.sec02Flux.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class Test {

    public static void main(String[] args) {


        Flux.range(1, 10)
                .subscribeWith(new Subscriber<Integer>() {

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        //subscription.request(Long.MAX_VALUE); //sin request no hay subscription
                    }

                    @Override
                    public void onNext(Integer next) {
                        System.out.println(next);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("done");
                    }

                });

    }
}

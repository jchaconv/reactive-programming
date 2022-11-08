package pe.com.reactive.sec01Mono;

import pe.com.reactive.util.Util;
import reactor.core.publisher.Mono;

public class Lec13MonoFromRunnable {

    public static void main(String[] args) {

        //Una forma de usar un runnable sencillo
        /*Runnable runnable = () -> System.out.println("");
        Mono.fromRunnable(runnable)
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());*/

        //Llamamos al runnable desde el método
        //lo que está en onComplete es un Runnable
        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(Util.onNext(),
                        Util.onError(),
                        () -> {
                            System.out.println("process is done. sending emails ...");
                        });
    }

    private static Runnable timeConsumingProcess() {
        return () -> {
            Util.sleepSeconds(3);
            System.out.println("Operation Completed");
        };
    }

}

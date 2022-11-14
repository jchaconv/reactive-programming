package pe.com.reactive.util;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.util.function.Consumer;

public class Util {

    /* Inicio Lec05 :: Use of Faker */
    private static final Faker FAKER = Faker.instance();
    /* Fin Lec05 :: Use of Faker */

    public static Consumer<Object> onNext() {
        return o -> System.out.println("Received :: " + o);
    }

    public static Consumer<Throwable> onError() {
        return err -> System.out.println("ERROR: " + err.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Completed");
    }


    /* Inicio Lec05 :: Use of Faker */
    public static Faker faker() {
        return FAKER;
    }
    /* Fin Lec05 :: Use of Faker */

    /* Inicio Lec09 :: Supplier Refactoring */
    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    /* Fin Lec09 :: Supplier Refactoring */

    /* INICIO SECCION 3 - LEC01 - default subscriber implementation */
    public static Subscriber<Object> subscriber() {
        return new DefaultSubscriber();
    }

    public static Subscriber<Object> subscriber(String name) {
        return new DefaultSubscriber(name);
    }
    /* FIN SECCION 3- LEC01 - default subscriber implementation */

}

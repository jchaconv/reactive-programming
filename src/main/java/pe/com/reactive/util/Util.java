package pe.com.reactive.util;

import com.github.javafaker.Faker;

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


}

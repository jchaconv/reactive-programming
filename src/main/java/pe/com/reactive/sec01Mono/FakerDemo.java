/* Inicio Lec05 :: Use of Faker */
package pe.com.reactive.sec01Mono;

import com.github.javafaker.Faker;

public class FakerDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            System.out.println(
                    Faker.instance().artist().name()
            );

        }

    }

}
/* Fin Lec05 :: Use of Faker */
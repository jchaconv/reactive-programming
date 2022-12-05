package pe.com.reactive.sec08CombiningPublishers;

import pe.com.reactive.sec08CombiningPublishers.helper.NameGenerator;
import pe.com.reactive.util.Util;

public class Lec01StartWith {

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("mike"));

        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("paul"));

        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("jake"));

        generator.generateNames()
                .filter(n -> n.startsWith("A"))
                .take(2)
                .subscribe(Util.subscriber("marshal"));

    }


}

package pe.com.reactive.sec01Mono.assignment;

import pe.com.reactive.util.Util;

public class AssignmentDemo {

    public static void main(String[] args) {

        FileService.read("file01.txt")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

        /*FileService.write("file02.txt", "reactive-programming!")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());*/

        FileService.delete("file01.txt")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

    }

}

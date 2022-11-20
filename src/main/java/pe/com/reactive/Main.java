package pe.com.reactive;

public class Main  implements Runnable {

    public static void main(String[] args) {

        new Thread(new Main()).start();

    }

    @Override
    public void run() {
        System.out.println("Hola Cris!");
    }
}
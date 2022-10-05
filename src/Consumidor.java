import javax.swing.*;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {
    private int id;
    private Semaphore semaforo;

    public Consumidor(int id, Semaphore semaforo) {
        this.id = id;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        while (true) {
            esperar();
            try {
                semaforo.acquire(1);
                consumir();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }

    public void esperar() {
        try {
            Programa.sistema.getInfoConsumidor().setText("Consumidor " + id + " esperando!");
            System.out.println("Consumidor " + id + " esperando!");
            Thread.sleep((long) (Math.random() * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumir() {
        try {
            Thread.sleep((long) (Math.random() * 10000));
            consumirArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumirArray() {
        for (int i = 0; i < Programa.buffer.length; i++) {
            if (Programa.buffer[i] != 0) {
                Programa.buffer[i] = 0;
                Programa.sistema.getInfoConsumidor().setText("Consumidor " + id + " consumiu!");
                System.out.println("Consumidor " + id + " consumiu!");
                Programa.sistema.getBuffer().setText(Arrays.toString(Programa.buffer));
                System.out.println(Programa.buffer[0] + " " + Programa.buffer[1] + " " + Programa.buffer[2]);
                break;
            }
        }
        esperar();
    }
}

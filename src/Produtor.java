import javax.swing.*;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Produtor extends Thread {
    private int id;
    private Semaphore semaforo;

    public Produtor(int id, Semaphore semaforo) {
        this.id = id;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        while (true) {
            esperar();
            try {
                semaforo.acquire(1);
                produzir();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }

    public void esperar() {
        try {
            Programa.sistema.getInfoProdutor().setText("Produtor " + id + " esperando!");
            System.out.println("Produtor " + id + " esperando!");
            Thread.sleep((long) (Math.random() * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void produzir() {
        try {
            Thread.sleep((long) (Math.random() * 10000));
            produzirArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void produzirArray() {
        for (int i = 0; i < Programa.buffer.length; i++) {
            if (Programa.buffer[i] == 0) {
                Programa.buffer[i] = (int) (Math.random() * 100);
                Programa.sistema.getInfoProdutor().setText("Produtor " + id + " produziu!");
                System.out.println("Produtor " + id + " produziu!");
                Programa.sistema.getBuffer().setText(Arrays.toString(Programa.buffer));
                System.out.println(Programa.buffer[0] + " " + Programa.buffer[1] + " " + Programa.buffer[2]);
                break;
            }
        }
        esperar();
    }
}

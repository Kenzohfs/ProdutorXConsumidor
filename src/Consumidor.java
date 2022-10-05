import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {
    private int id;
    private Semaphore semaforo;
    private String status;

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
            this.status = "Consumidor " + id + " esperando";
            Programa.atualizarLabels();
            System.out.println("Consumidor " + id + " esperando!");
            Thread.sleep((long) (Math.random() * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumir() {
        try {
            this.status = "Consumidor " + id + " consumindo";
            Programa.atualizarLabels();
            Thread.sleep((long) (Math.random() * 10000));
            consumirArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumirArray() {
        if (Programa.buffer.size() > 0) {
            Programa.buffer.pop();
            System.out.println("Consumidor " + id + " consumiu!");
            Programa.sistema.getBuffer().setText(Programa.buffer.toString());
            System.out.println(Programa.buffer.toString());
        }
        esperar();
    }

    public String getStatus() {
        return status;
    }
}

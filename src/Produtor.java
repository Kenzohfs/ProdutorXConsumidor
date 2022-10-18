import java.util.concurrent.Semaphore;

public class Produtor extends Thread {
    private int id;
    private Semaphore semaforo;
    private String status;
    private Boolean rodar = true;

    public Produtor(int id, Semaphore semaforo) {
        this.id = id;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        while (rodar) {
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
            this.status = "Produtor " + id + " esperando";
            Programa.atualizarLabels();
            System.out.println("Produtor " + id + " esperando!");
            Thread.sleep((long) (Math.random() * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void produzir() {
        try {
            this.status = "Produtor " + id + " produzindo";
            Programa.atualizarLabels();
            Thread.sleep((long) (Math.random() * 10000));
            produzirArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void produzirArray() {
        if (Programa.buffer.size() < 5) {
            Programa.buffer.push((int) (Math.random() * 100));
            System.out.println("Produtor " + id + " produziu!");
            Programa.sistema.getBuffer().setText(Programa.buffer.toString());
            System.out.println(Programa.buffer.toString());
        }
        esperar();
    }

    public String getStatus() {
        return status;
    }

    public void setRodar(Boolean rodar) {
        this.rodar = rodar;
    }
}

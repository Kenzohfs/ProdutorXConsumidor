import java.util.concurrent.Semaphore;

public class Main{
    //trocar para stack
//    static int[] buffer = new int[3];
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1);
        Produtor[] produtores = new Produtor[3];
        Consumidor[] consumidores = new Consumidor[3];

        for (int i = 0; i < produtores.length; i++) {
            produtores[i] = new Produtor(i, semaforo);
            produtores[i].start();
        }

        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i] = new Consumidor(i, semaforo);
            consumidores[i].start();
        }

//        Produtor produtor = new Produtor(1, semaforo);
//        Consumidor consumidor1 = new Consumidor(1, semaforo);
//        Consumidor consumidor2 = new Consumidor(2, semaforo);
//
//        produtor.start();
//        consumidor1.start();
//        consumidor2.start();
    }
}
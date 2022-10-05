import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class Programa extends JFrame implements Runnable {
    private JPanel programa;
    private JButton startButton;
    private JButton stopButton;
    private JLabel infoBuffer;
    private JLabel infoProdutor;
    private JLabel infoConsumidor;
    public static Programa sistema;
    static int[] buffer = new int[5];
    static Semaphore semaforo = new Semaphore(1);
    static Produtor[] produtores = new Produtor[3];
    static Consumidor[] consumidores = new Consumidor[2];

    public static void main(String[] args) {
        sistema = new Programa();
    }

    public Programa() {
        criarComponentes();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < produtores.length; i++) {
                    produtores[i] = new Produtor(i, semaforo);
                    produtores[i].start();
                }
                for (int i = 0; i < consumidores.length; i++) {
                    consumidores[i] = new Consumidor(i, semaforo);
                    consumidores[i].start();
                }
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < produtores.length; i++) {
                    produtores[i].stop();
                }
                for (int i = 0; i < consumidores.length; i++) {
                    consumidores[i].stop();
                }
            }
        });
    }

    private void criarComponentes() {
        setContentPane(programa);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        run();
        pack();
    }

    @Override
    public void run() {
        if(!isVisible()){
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "A janela ja está aberta!");
        }
    }

    public JLabel getBuffer() {
        return infoBuffer;
    }

    public JLabel getInfoProdutor() {
        return infoProdutor;
    }

    public JLabel getInfoConsumidor() {
        return infoConsumidor;
    }
}

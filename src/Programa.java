import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.concurrent.Semaphore;

public class Programa extends JFrame implements Runnable {
    private JPanel programa;
    private JButton startButton;
    private JButton stopButton;
    private JLabel infoBuffer;
    private JLabel infoProdutor1;
    private JLabel infoConsumidor1;
    private JLabel infoProdutor2;
    private JLabel infoConsumidor2;
    private JLabel infoProdutor3;
    public static Programa sistema;
    static Stack<Integer> buffer = new Stack<>();
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
                    produtores[i].setRodar(false);
                }
                for (int i = 0; i < consumidores.length; i++) {
                    consumidores[i].setRodar(false);
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

    public static void atualizarLabels() {
        Programa.sistema.getInfoProdutor().setText(produtores[0].getStatus());
        Programa.sistema.infoProdutor2().setText(produtores[1].getStatus());
        Programa.sistema.infoProdutor3().setText(produtores[2].getStatus());
        Programa.sistema.getInfoConsumidor().setText(consumidores[0].getStatus());
        Programa.sistema.infoConsumidor2().setText(consumidores[1].getStatus());
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
        return infoProdutor1;
    }

    public JLabel infoProdutor2() {
        return infoProdutor2;
    }

    public JLabel infoProdutor3() {
        return infoProdutor3;
    }

    public JLabel getInfoConsumidor() {
        return infoConsumidor1;
    }

    public JLabel infoConsumidor2() {
        return infoConsumidor2;
    }
}

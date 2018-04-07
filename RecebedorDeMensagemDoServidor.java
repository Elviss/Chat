
import java.io.InputStream;
import java.util.Scanner;

public class RecebedorDeMensagemDoServidor implements Runnable {

    private InputStream servidor;

    public RecebedorDeMensagemDoServidor(InputStream servidor) {
        this.servidor = servidor;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        try (Scanner s = new Scanner(this.servidor)){
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
        }
    }
}

package chatDois;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TratadorDeMensagemDoCliente implements Runnable {

    private Socket cliente;
    private Servidor servidor;

    public TratadorDeMensagemDoCliente(Socket cliente, Servidor servidor) {
        this.cliente = cliente;
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
        try(Scanner s = new Scanner(this.cliente.getInputStream())) {
            while (s.hasNextLine()) {
                servidor.distribuiMensagem(this.cliente, s.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private String host;
    private int porta;

    public Client(String host, int porta) {
        this.host = host;
        this.porta = porta;
    }

    public void executa() throws UnknownHostException, IOException {

        try (Socket client = new Socket(this.host, this.porta);
             Scanner teclado = new Scanner(System.in);
             PrintStream saida = new PrintStream(client.getOutputStream())) {

            System.out.println("O cliente se conectou ao servidor");

            RecebedorDeMensagemDoServidor r = new RecebedorDeMensagemDoServidor(client.getInputStream());
            new Thread(r).start();

            while (teclado.hasNextLine()) {
                saida.println(teclado.nextLine());
            }
        }

    }
}

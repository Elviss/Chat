package chatDois;

import chatDois.TratadorDeMensagemDoCliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private int porta;
    private List<Socket> clientes;

    public Servidor(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<>();
    }

    public void executa() throws IOException {
        try(ServerSocket servidor = new ServerSocket(this.porta)) {
            System.out.println("Porta 1234 aberta!");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

                this.clientes.add(cliente);

                TratadorDeMensagemDoCliente tc = new TratadorDeMensagemDoCliente(cliente,this);
                new Thread(tc).start();
            }
        }
    }

    public void distribuiMensagem(Socket clienteQueEnviou, String msg) {
        for (Socket cliente : this.clientes) {
            if(!cliente.equals(clienteQueEnviou)) {
                try {
                    PrintStream ps = new PrintStream(cliente.getOutputStream());
                    ps.println("Vc: "+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

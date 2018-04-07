package dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Servidor extends Thread {

    private static ArrayList<BufferedWriter>clientes;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;

    public Servidor(Socket con) {
        this.con = con;

        try {
            in = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            String msg;
            OutputStream out = this.con.getOutputStream();
            Writer outw = new OutputStreamWriter(out);
            BufferedWriter bfw = new BufferedWriter(outw);

            clientes.add(bfw);
            nome = msg = bfr.readLine();

            while (!"Sair".equalsIgnoreCase(msg) && msg != null) {

                msg = bfr.readLine();
                sendToAll(bfw, msg);
                System.out.println(msg);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Método usado para enviar mensagem para todos os clients
     * @param bwSaida do tipo BufferedWriter
     * @param msg do tipo String
     * @throws IOException
     */
    private void sendToAll(BufferedWriter bwSaida, String msg) throws IOException {

        BufferedWriter bwS;

        for (BufferedWriter bw : clientes){

            bwS = (BufferedWriter) bw;

            if(!(bwSaida == bwS)){
                bw.write(nome + " -> " + msg + "\r\n");
                bw.flush();
            }

        }

    }
}

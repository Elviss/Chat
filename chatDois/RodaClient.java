package chatDois;

import chatDois.Client;

import java.io.IOException;
import java.net.UnknownHostException;

public class RodaClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client("127.0.0.1", 1234).executa();
    }
}

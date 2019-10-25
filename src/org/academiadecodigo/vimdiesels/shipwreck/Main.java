package org.academiadecodigo.vimdiesels.shipwreck;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.run(8090);

    }
}

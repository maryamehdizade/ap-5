import server.server;

import static costant.Constant.PORT;

public class Main {
    public static void main(String[] args) {
        new server(PORT).listen();
    }
}

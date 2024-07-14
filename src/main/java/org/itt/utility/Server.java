package org.itt.utility;

import org.itt.controller.LoginController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

public class Server {

    private static final Logger logger = Logger.getLogger(Server.class.getName());
    private static final String PORT_NUMBER =System.getenv("PORT_NUMBER");

    static {
        try {
            FileHandler fileHandler = new FileHandler("user_activity.log", true);
            fileHandler.setFormatter(new org.itt.utility.CustomFormatter());
            logger.addHandler(fileHandler);

            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT_NUMBER))) {
            System.out.println("Server is listening on port : "+PORT_NUMBER);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
                new LoginController(socket).start();
            }
        } catch (IOException e) {
            System.out.println("unable to connect please try again : "+ e.getMessage());
        }
    }

    public static void logActivity(String action, int userId) {
        logger.log(Level.INFO, action, new Object[]{userId});
    }
}

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());
    private static HttpServer server;
    public static void main(String[] args) {
        init(args);
        startServer();
    }

    public static void init(String[] args){
        new ApplicationProperties().importPropertiesFromFile(args[0]);
    }


    public static void startServer(){
        try {
            logger.info("Starting Server");
            server = HttpServer.create(new InetSocketAddress(Integer.valueOf(ApplicationProperties.getProperty(Utils.serverPort))), 0);
            server.createContext("/", new RequestHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            logger.info("Server successfully started");
        } catch (Exception e) {
            logger.info("Error occurred while starting Server");
            throw new RuntimeException(e);
        }
    }

}
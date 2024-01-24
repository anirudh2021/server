import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Logger;

import com.sun.net.httpserver.*;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestHandler implements HttpHandler {

    static Logger logger = Logger.getLogger(RequestHandler.class.getName());
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            logger.info("Request received");
            if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                postRequestHandler(exchange);
                sendResponse(exchange, 200, "Success");
            } else {
                logger.info("Request with method "+ exchange.getRequestMethod());
                sendResponse(exchange, 400, "Resource not found.");
            }
        } catch (Exception e) {
            logger.warning("Error handling request");
            sendResponse(exchange, 500, "System error");
        }
    }

    private void postRequestHandler(HttpExchange exchange) throws IOException, InterruptedException {
        try {
            logger.info("Processing message");
            byte[] requestBody = exchange.getRequestBody().readAllBytes();

            JSONObject requestJsonObject = new JSONObject(new String(requestBody, StandardCharsets.UTF_8));
            StringBuilder delimitedContent = new StringBuilder();
            for (String key : requestJsonObject.keySet()) {
                delimitedContent.append(key).append(ApplicationProperties.getProperty(Utils.delimiter)).append(requestJsonObject.getString(key)).append("\n");
            }

            Path requestFile = Paths.get(ApplicationProperties.getProperty(Utils.directoryPath), new Date().getTime() + ApplicationProperties.getProperty(Utils.fileNameSuffix));
            Files.createDirectories(requestFile.getParent());
            Files.writeString(requestFile, delimitedContent);
            logger.info("Message successfully saved to "+ requestFile.getFileName().toString());
        } catch (Exception e) {
            logger.warning("Error handling message: " + e.getMessage());
            sendResponse(exchange, 400, "An error occurred while processing the message.");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String responseBody) throws IOException {
        byte[] responseBytes = responseBody.getBytes();
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }
}
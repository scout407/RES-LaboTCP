import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CalculatorClient {
    static void main(String[] args){
        Socket clientSocket = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            clientSocket = new Socket("El_Calculator", 4666);
            os = clientSocket.getOutputStream();
            is = clientSocket.getInputStream();

            final GsonBuilder builder = new GsonBuilder();
            final Gson gson = builder.create();

            gson.toJson("+");
            gson.toJson("2");
            gson.toJson("3");

            os.write(gson.toString().getBytes());

            ByteArrayOutputStream respond = new ByteArrayOutputStream();
            byte[] buffer = new byte[250];
            int newByte;

            while ((newByte = is.read(buffer)) != -1){
                respond.write(buffer, 0, newByte);
            }

            System.out.println(respond);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

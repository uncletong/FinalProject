package hk.polyu.dc.wifimeasure;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SendMessage implements Runnable{
    final private String IP = "159.65.148.98";
    final int PORT = 9999;
    private Context context;
    private Socket socket;
    private JSONObject jsonObject;
    private DataOutputStream outputStream;

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    socket = new Socket(IP, PORT);
                    Log.i("socket", socket.isConnected() + "");
                    String jsonString = jsonObject.toString();
                    byte[] jsonByte = jsonString.getBytes();
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.write(jsonByte);
                    outputStream.flush();
                    socket.shutdownOutput();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        },1000*60*10, 1000*60*10);

    }
}

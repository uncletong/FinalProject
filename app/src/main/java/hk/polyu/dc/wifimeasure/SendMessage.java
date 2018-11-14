package hk.polyu.dc.wifimeasure;

import android.util.Log;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SendMessage implements Runnable{
    final private String IP = "159.65.148.98";
    final int PORT = 9999;
    private Socket socket;
    private JSONArray jsonArray;
    private DataOutputStream outputStream;
    private MapsActivity mapsActivity;

    public SendMessage(MapsActivity mapsActivity) {
        this.mapsActivity = mapsActivity;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    socket = new Socket(IP, PORT);
                    Log.i("socket", socket.isConnected() + "");
                    jsonArray = mapsActivity.getJsonArray();
                    String jsonString = jsonArray.toString();
                    byte[] jsonByte = jsonString.getBytes();
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.write(jsonByte);
                    outputStream.flush();
                    socket.shutdownOutput();
                    socket.close();
                    mapsActivity.clearJson();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        },1000*10, 1000*10);

    }
}

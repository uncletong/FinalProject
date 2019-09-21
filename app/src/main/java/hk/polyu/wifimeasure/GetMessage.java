package hk.polyu.wifimeasure;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GetMessage implements Runnable{
    private MapsActivity mapsActivity;
    private int messageKey;

    GetMessage(MapsActivity mapsActivity, int messageKey){
        this.mapsActivity = mapsActivity;
        this.messageKey = messageKey;
    }

    @Override
    public void run() {
        try {

                int PORT = 9998;
                String IP = "203.195.247.237";
                Socket socket = new Socket(IP, PORT);
                Log.i("socket", socket.isConnected() + "receive");
                String key = messageKey + "";
                byte[] keyByte = key.getBytes();
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.write(keyByte);
                dataOutputStream.flush();
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(),"utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    String[] data = line.split(" ");
                    Double latitude = Double.valueOf(data[0]);
                    Double longitude = Double.valueOf(data[1]);
                    if (key.equals("1")){
                        mapsActivity.circleAdd1(latitude,longitude);

                    }else if (key.equals("2")){
                        mapsActivity.circleAdd2(latitude,longitude);

                    }else if (key.equals("3")){
                        mapsActivity.circleAdd3(latitude,longitude);

                    }else if (key.equals("4")){
                        mapsActivity.circleAdd4(latitude,longitude);

                    }
                }
                dataOutputStream.close();
                inputStreamReader.close();
                bufferedReader.close();
                socket.close();
                /*DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String str_input = dataInputStream.readUTF();*/
                /*JSONArray jsonArray = new JSONArray(builder.toString());
                for (int j = 0; j < jsonArray.length(); j++){
                    JSONObject lan = jsonArray.getJSONObject(j);
                    map.addCircle(new CircleOptions()
                        .center(new LatLng(lan.getDouble("latitude"),lan.getDouble("longitude")))
                        .radius(2)
                        .fillColor(0xAAFFFF00)
                        .strokeColor(0xAAFFFF00));
                }*/
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

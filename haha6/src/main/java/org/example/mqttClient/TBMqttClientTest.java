package org.example.mqttClient;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.example.iot.serial.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TBMqttClientTest {
    public static void main(String[] args) throws MqttException {
        MqttConfig config = JaxbUtil.xmlToBean("mqttConfig.xml", MqttConfig.class);
//        int[] data={1,0,1,0,0,0,0,0};
        List<Integer> list = new ArrayList<Integer>();
//        for (int i=0;i<9;i++){
//            list.add(0);
//        }
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        Map<String, List<Integer>> teledata = new HashMap<String, List<Integer>>();
        teledata.put("dOutput", list);
        TBGWMqttClient client = null;

        client = new TBGWMqttClient(config);

        client.subscribe(config.getRpcEchoTopic());
        Gson gson = new Gson();
        client.publish(config.getTopic(), gson.toJson(teledata));

    }
}

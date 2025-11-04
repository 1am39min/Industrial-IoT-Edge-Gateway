package org.example.mqttClient;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.example.iot.serial.Message;
import org.example.iot.serial.SerialComListener;

import java.util.HashMap;
import java.util.Map;

public class TBGWMqttClient extends RPCsubject implements MqttCallback, SerialComListener {
    private MqttClient client;
    public MqttConfig config;
    Gson gson = new Gson();

    public TBGWMqttClient(MqttConfig config) throws MqttException {
        this.config = config;
        run();
    }

    void run() throws MqttException {

        client = new MqttClient(config.getBroker(), config.getClientID(), new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(config.getUsername());
        options.setPassword(config.getPassword().toCharArray());
        options.setConnectionTimeout(500);
        options.setKeepAliveInterval(100);
        //options.setAutomaticReconnect(true);
        client.setCallback(this);

        client.connect(options);

    }

    public void publish(String topic, String message) {
        try {
            this.client.publish(topic, message.getBytes(), 1, true);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }

    public void subscribe(String topic) {
        try {
            this.client.subscribe(topic);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("连接失败");

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        System.out.println("======监听到来自[" + topic + "]的消息======");
        System.out.println("message content:" + new String(message.getPayload()));

        //分析
        String info = new String(message.getPayload());
        RPCMessage rpcMessage = gson.fromJson(info, RPCMessage.class);
        System.out.println(info);

        //设置响应
        String rpcEchoTopic = topic.replace("request", "response");
        //System.out.println("rpc topic:"+rpcEchoTopic);
        //响应数据
        Map<String, Boolean> echoMap = new HashMap<String, Boolean>();
        echoMap.put(Integer.toString(rpcMessage.getPin()), rpcMessage.isEnabled());
        //上传响应
        client.publish(rpcEchoTopic, gson.toJson(echoMap).getBytes(), 1, false);

        //发布rpc消息
        notifyObserverUpdate(message);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("发送完成");

    }
    public String swqp666(String s){
        String result = s.replaceAll("false","0").replaceAll("true","1").replace("}","]").replace("{","[").replace( "0=","").replace( "1=","").replace( "2=","").replace( "3=","");
        return result;
    }

    @Override
    public void update(Message message) {
        //subscribe(config.getRpcEchoTopic());
        String senddata ="{"
                + "\"sensorModel\": \"T1000\","
                + "\"weight\":" + message.getWeight() + ","
                + "\"dInput\":" + swqp666(message.getdInput().toString()) + ","
                + "\"dOutput\":" + swqp666(message.getdOutput().toString()) + ","
                + "\"isChanged\":" + message.isChanged() + ","
                + "\"isopen\":" + message.isIsopen() +
                "}";
        System.out.println("???"+ swqp666(message.getdInput().toString()));

        publish(config.getTopic(), senddata);

    }
}
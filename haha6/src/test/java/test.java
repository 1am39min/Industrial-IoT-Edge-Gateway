import org.eclipse.paho.client.mqttv3.MqttException;
import org.example.iot.database.Work;
import org.example.iot.serial.SerialCom;
import org.example.mqttClient.JaxbUtil;
import org.example.mqttClient.MqttConfig;
import org.example.mqttClient.TBGWMqttClient;


public class test {
    public static void main(String[] args) throws MqttException {
        SerialCom serialCom =new SerialCom();

        Work work = new Work();
        serialCom.registerObserver(work);

        MqttConfig mqttConfig= JaxbUtil.xmlToBean("mqttConfig.xml", MqttConfig.class);
        TBGWMqttClient tbgwMqttClient=new TBGWMqttClient(mqttConfig);
        serialCom.registerObserver(tbgwMqttClient);

        //tbgwMqttClient.publish(mqttConfig.getTopic(), Message.getinstance().toString());
        tbgwMqttClient.subscribe("sensor/Gpio/request/setGpioStatus/+");
    }


}

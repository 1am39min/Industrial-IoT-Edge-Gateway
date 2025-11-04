package org.example.iot.kelo;


import org.eclipse.paho.client.mqttv3.MqttException;
import org.example.iot.serial.SerialCom;
import org.example.iot.serial.SerialParameter;
import org.example.iot.ui.MainView;

public class MainApp {

	public static void main(String[] args) throws MqttException {

		SerialParameter serialParameter = SerialParameter.getinstance();
		SerialCom serialCom = SerialCom.getinstance();

		// TODO Auto-generated method stub
		MainView mainView = new MainView(serialParameter, serialCom);
		mainView.setVisible(true);
		
		TimeTask timeTask = new TimeTask(serialCom);
		timeTask.start();
		
	}

}

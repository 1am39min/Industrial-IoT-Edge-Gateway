package org.example.iot.database;


import org.example.iot.serial.Message;
import org.example.iot.serial.SerialCom;
import org.example.iot.serial.SerialComListener;
import org.example.iot.serial.SerialProtocol;

public class Work implements SerialComListener {
	

	@Override
	public void update(Message message) {
		// TODO Auto-generated method stub
		OperateRecorder operateRecorder = OperateRecorder.getinstance();
		if(message.getWeight()>operateRecorder.getMaxweight()) {
			
			byte[] sendCmd =  SerialProtocol.getDouputCmd(3, true);
			SerialCom serialCom = SerialCom.getinstance();
			serialCom.sendCom(sendCmd);
			
			operateRecorder.setEndWight(message.getWeight());
		}
		
		
		
	}
	
	

}

package org.example.iot.kelo;

import org.example.iot.serial.SerialCom;
import org.example.iot.util.ByteUtils;

public class TimeTask extends Thread {
	private SerialCom serialCom;

	public TimeTask(SerialCom serialCom) {
		super();
		this.serialCom = serialCom;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			//�õ�����
			byte[] bb = ByteUtils.hexToByteArray("0141C010");
			//�·�����
			serialCom.sendCom(bb);
			//����
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

}

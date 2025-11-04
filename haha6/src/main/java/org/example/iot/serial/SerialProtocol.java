package org.example.iot.serial;

import org.example.iot.database.OperateRecorder;
import org.example.iot.util.ByteUtils;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Iterator;

import javax.xml.transform.SourceLocator;



public class SerialProtocol {
	public static final String outputState = "01050000FF008C3A";
	public static final String peelCMD = "014201D160";
	
	
	//��Ϣ�ַ���ת����message�������
	
	public static Message getState(byte[] buf, int len) {
		Message message = Message.getinstance();
		int i = 0;
		int cmdLen = 0;
		
		//��ȡ��������(20-23)
		byte[] weightHex = Arrays.copyOfRange(buf, 20, 24);
		int weight = ByteUtils.byteArrayToIntLE(weightHex);
		message.setWeight(weight);
		
//		System.out.println(message.getWeight());
		
		//��ť�������
		for (int j = 0; j < 4; j++) {
			if ((buf[2]&(0x01<<j))!=0) {
				message.getdInput().put(j, true);
				
			} else {
				message.getdInput().put(j, false);
	
			}
//			System.out.println(message.getdInput().get(j));
			
			if ((buf[3]&(0x01<<j))!=0) {
				message.getdOutput().put(j, true);
				
			} else {
				message.getdOutput().put(j, false);
	
			}
//			System.out.println(message.getdOutput().get(j));

		
		}
		
		OperateRecorder operateRecorder = OperateRecorder.getinstance();
		if(weight > operateRecorder.getMaxweight()) {
			//TO DO......
			byte[] sendCmd = getDouputCmd(3, true);
			SerialCom serialCom = SerialCom.getinstance();
			serialCom.sendCom(sendCmd);
			
			operateRecorder.setEndWight(weight);
			
		}

		message.setChanged(true);
		
		return message;
		
		
		
		
	}
	
	public static byte[] getDouputCmd(int i, boolean isOpened) {
		byte[] dataBytes = ByteUtils.hexToByteArray(outputState);
		dataBytes[2] = (byte)((i >> 8)&0xff);
		dataBytes[3] = (byte)(i & 0xff);
		
		if(isOpened) dataBytes[4] = (byte)(0x00);
		
		int crc = ByteUtils.getCrcInt(Arrays.copyOfRange(dataBytes, 0, 6));
		dataBytes[6] = (byte)(crc & 0xff);
		dataBytes[7] = (byte)((crc>>8) & 0xff);
		
		return dataBytes;
		
	}
	
	public static byte[] getCalibration(int caliWeight, int calTime) {
		
		byte[] dataBytes = ByteUtils.hexToByteArray("01430500000000C933");
		dataBytes[2] = (byte)(calTime & 0xff);
		dataBytes[3] = (byte)(caliWeight & 0xff);
		dataBytes[4] = (byte)((caliWeight >> 8)&0xff);
		dataBytes[5] = (byte)((caliWeight >> 16)&0xff);
		dataBytes[6] = (byte)((caliWeight >> 24)&0xff);
		
		int crc = ByteUtils.getCrcInt(Arrays.copyOfRange(dataBytes, 0, 7));
		dataBytes[7] = (byte)(crc & 0xff);
		dataBytes[8] = (byte)((crc >> 8) & 0xff);
		
		return dataBytes;
		
	}

}

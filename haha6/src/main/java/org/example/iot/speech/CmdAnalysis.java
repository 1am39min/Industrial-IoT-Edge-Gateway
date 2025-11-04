package org.example.iot.speech;

import org.example.iot.serial.Message;
import org.example.iot.serial.SerialCom;
import org.example.iot.serial.SerialParameter;
import org.example.iot.serial.SerialProtocol;
import org.example.iot.ui.CalibrationView;
import org.example.iot.ui.SerialConfig;
import org.example.iot.util.ByteUtils;
import org.example.iot.util.JsonUtil;
import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;


import javazoom.jl.decoder.JavaLayerException;

public class CmdAnalysis{
	
	
	public static String cmdrecog(AipSpeech client) {
		String cmd = null;
		String audioFile = "D:\\Documents\\eclipse-workspace\\keloV3\\resource\\011.wav";
		AudioRecorder audioRecorder = new AudioRecorder(audioFile);
		audioRecorder.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		audioRecorder.stopRecording();
		JSONObject res1 = VoiceRecognition.audioToString(client,audioFile);
		JsonRootBean resultElement1 = JsonUtil.fromJsontos(res1.toString(), JsonRootBean.class);
		
		if (resultElement1.getResult()!=null) {
			cmd = resultElement1.getResult().get(0);
			System.out.println(cmd);
			return cmd;
			
		} else {
			return null;
		}
		
	}
	
	public static void CmdExcute(AipSpeech client, String cmdString, SerialCom serialCom, SerialParameter serialParameter) {
		
		if (cmdString.contains("���ô���")||cmdString.contains("���ô���")) {
			SerialConfig serialConfig = new SerialConfig();
			serialConfig.setVisible(true);
			SpeechSynthesis.speechSynthesis(client,"��Ϊ���򿪴�������", "audio1.mp3");
			try {
				MusicPlayer.mp3Player("audio1.mp3");
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (cmdString.contains("�򿪴���")||cmdString.contains("�򿪴���")) {
			serialCom.openPort(serialParameter);
			byte[] bb = ByteUtils.hexToByteArray("0141C010");
			serialCom.sendCom(bb);
			
		}else if (cmdString.contains("�رմ���")||cmdString.contains("�رմ���")) {
			serialCom.closePort();
			
		}else if (cmdString.contains("У׼����")||cmdString.contains("�궨����")) {
			CalibrationView calibrationView = new CalibrationView(serialCom);
			calibrationView.setVisible(true);
			
		}else if (cmdString.contains("һ·���")) {
			boolean state = Message.getinstance().getdOutput().get(0);
			byte[] sendCmd = SerialProtocol.getDouputCmd(0, state);
			serialCom.sendCom(sendCmd);
		}else if (cmdString.contains("��·���")) {
			boolean state = Message.getinstance().getdOutput().get(1);
			byte[] sendCmd = SerialProtocol.getDouputCmd(1, state);
			serialCom.sendCom(sendCmd);
		}else if (cmdString.contains("��·���")) {
			boolean state = Message.getinstance().getdOutput().get(2);
			byte[] sendCmd = SerialProtocol.getDouputCmd(2, state);
			serialCom.sendCom(sendCmd);
		}else if (cmdString.contains("��·���")) {
			boolean state = Message.getinstance().getdOutput().get(3);
			byte[] sendCmd = SerialProtocol.getDouputCmd(3, state);
			serialCom.sendCom(sendCmd);
		}
		
		
		
		
		
		
		
	}



	
	

}

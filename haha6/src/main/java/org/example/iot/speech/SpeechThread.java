package org.example.iot.speech;

import com.baidu.aip.speech.AipSpeech;
import org.example.iot.serial.SerialCom;
import org.example.iot.serial.SerialParameter;


public class SpeechThread extends Thread {
	
	private SerialCom serialCom;
	private SerialParameter serialParameter;
	private boolean state;
	private AipSpeech client;
	

	public SpeechThread(AipSpeech client,Boolean state,SerialCom serialCom,SerialParameter serialParameter) {
		super();
		this.serialCom = serialCom;
		this.serialParameter = serialParameter;
		this.state = state;
		this.client = client;
	}
	
	@Override
	public void run() {
		while(state) {
			String cmdString = CmdAnalysis.cmdrecog(client);
			if(cmdString != null) {
				CmdAnalysis.CmdExcute(client,cmdString, serialCom, serialParameter);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				continue;
			}
			
		}
		
	}

	public void exit() {
		// TODO Auto-generated method stub
		state = false;
	}
	

}

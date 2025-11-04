package org.example.iot.face;


import com.baidu.aip.face.AipFace;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;
import org.example.iot.util.JsonUtil;
import org.json.JSONObject;

import javax.swing.*;

public class Demo {
	private static JFrame window;
	static JSONObject result;
	static JsonRootBean resultElement;
	static int err_code;
	static int i;

	public static void main(String[] args) {
		
		// ��ȡĬ�ϵ�����ͷʵ��
        final Webcam webcam = Webcam.getDefault();
        // ��������ͷ�����ͼ��ߴ�ΪVGA
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        // ����һ��WebcamPanel���󣬲�������ͷʵ�����ݸ���
        WebcamPanel panel = new WebcamPanel(webcam);
        // ������ͼ���������ʾFPS��������Ϣ��ͼ��ߴ磬�����о�����ʾ
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);
        // ����һ��JFrame���ڶ��󣬲�������ͷ�����ӵ�������
        window = new JFrame("����ͷ");
        window.add(panel);
        // ���ô��ڵ�һЩ���ԺͿɼ���
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        
        window.setResizable(true);
        window.pack();
        window.setVisible(true);
        
        String fileNameString = "001";
        
        // TODO Auto-generated method stub
     	AipFace client = GetAuth.getToken();
		
     	for (i = 0; i < 3; i++) {
     		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     		
        	WebcamUtils.capture(webcam, fileNameString, ImageUtils.FORMAT_PNG);
        	result = GetInfo.search(client);
    		resultElement = JsonUtil.fromJsontof(result.toString(), JsonRootBean.class);
    		err_code = resultElement.getError_code();
    		
    		if (err_code == 0) {
    			break;
			}
    		
		}
     	if(i >= 3) {
     		System.out.println("δ�ҵ����û�");
     	}else {
			System.out.println("�ɹ�����");
		}
		
		String userId = resultElement.getResult().getUser_list().get(0).getUser_id();
		System.out.println(err_code);
		System.out.println(userId);
		
		

	}

}

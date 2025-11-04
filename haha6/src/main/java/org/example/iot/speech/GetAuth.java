package org.example.iot.speech;

import com.baidu.aip.speech.AipSpeech;

public class GetAuth {
	public static final String APP_ID = "�滻Ϊ�Լ���APP ID";//�����Լ��ڰٶ�������ע������ʶ��Ӧ�ú󣬻�ȡ��APP_ID
    public static final String API_KEY = "�滻Ϊ�Լ���API_KEY ";//���ɻ�ȡ��API_KEY
    public static final String SECRET_KEY = "�滻Ϊ�Լ���SECRET_KEY";//���ɻ�ȡ��SECRET_KEY
    
    public static AipSpeech getToken() {
		AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
		
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(2000);
		return client;
		
	}
    
}

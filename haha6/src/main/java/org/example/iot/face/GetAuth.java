package org.example.iot.face;

import com.baidu.aip.face.AipFace;

public class GetAuth {
	public static final String APP_ID = "�滻Ϊ�Լ���APP ID"; //�����Լ��ڰٶ�������ע������ʶ��Ӧ�ú󣬻�ȡ��APP_ID
    public static final String API_KEY = "�滻Ϊ�Լ���API_KEY "; //���ɻ�ȡ��API_KEY
    public static final String SECRET_KEY = "�滻Ϊ�Լ���SECRET_KEY"; //���ɻ�ȡ��SECRET_KEY
    
    public static AipFace getToken() {
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(2000);
		return client;
		
	}
    
}

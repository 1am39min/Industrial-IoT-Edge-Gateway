package org.example.iot.face;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class GetInfo {
	public static JSONObject search(AipFace client) {
		String filepath = "capture.png";
		 // �����ѡ�������ýӿ�
	    HashMap<String, Object> options = new HashMap<>();
	    options.put("match_threshold", "70");
	    options.put("quality_control", "NORMAL");
	    options.put("face_sort_type", 1);
	    
	    String image = GetFileContentAsBase64.getFileContentAsBase64(filepath);
	    String imageType = "BASE64";
	    String groupIdList = "group1";
	    
	    // ��������
	    JSONObject res = client.search(image, imageType, groupIdList, options);
	    return res;

	}

}

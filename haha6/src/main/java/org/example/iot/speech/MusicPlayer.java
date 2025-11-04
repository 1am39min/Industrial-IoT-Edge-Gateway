package org.example.iot.speech;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
	 public static void mp3Player(String filename) throws JavaLayerException {
		//����һ��File����
		 File file=new File(filename);
		 try {
			//����һ��������
			FileInputStream stream=new FileInputStream(file);
			//����һ��������
			BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
			//�������������󣬰��ļ��Ļ����������ȥ
			Player player=new Player(bufferedInputStream);
			//���ò��ŷ������в���
			player.play();
			//player.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
}

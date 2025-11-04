package org.example.iot.speech;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class AudioRecorder extends Thread {

	private static TargetDataLine mic;
    private String audioName;

    public AudioRecorder(String audioName) {
         this.audioName = audioName;
    }

    @Override
    public  void run() {
        initRecording();
        statRecording();
    }

    private void initRecording() {

        System.out.println("��ʼ¼��.....");

        try {
            //define audio format
        	//�����ʽ�������ʣ�ÿ��������λ����������֡�����ֽڣ���֡�����Ƿ�big-endian�ֽ�˳��洢
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000, 16, 1, 2, 160000, false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
            // ��ȡ��Ƶ�����豸
            mic = (TargetDataLine) AudioSystem.getLine(info);
            mic.open();

            System.out.println("¼����......");
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }

    }

    private void statRecording() {
        try {
        	// ����ʼ��Ƶ�����ط�ʱ������ START �¼�
        	mic.start();
            // �����ָʾ��Ŀ�������ж�ȡ���ݵ���Ƶ������
            AudioInputStream audioInputStream = new AudioInputStream(mic);
            File f = new File(audioName);
            
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, f);
            System.out.println("¼���ļ��洢.....");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

       public void stopRecording() {
        mic.stop();
        mic.close();
        System.out.println("¼������....");
    }
}

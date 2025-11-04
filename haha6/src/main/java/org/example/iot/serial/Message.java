package org.example.iot.serial;

import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.Map;

public class Message extends SerialCom {
    //��������
    private int weight;
    //������˿�״̬
    private Map<Integer, Boolean> dInput = new HashMap();
    //������˿�״̬
    private Map<Integer, Boolean> dOutput = new HashMap();
    //��Ϣ�Ƿ񱻸ı�
    private boolean isChanged = false;
    private boolean isopen = false;


    //������չ����ǰδʹ��
//	@SuppressWarnings("unused")
//	private int humid = 0;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Map<Integer, Boolean> getdInput() {
        return dInput;
    }

    public void setdInput(Map<Integer, Boolean> dInput) {
        this.dInput = dInput;
    }

    public Map<Integer, Boolean> getdOutput() {
        return dOutput;
    }

    public void setdOutput(Map<Integer, Boolean> dOutput) {
        this.dOutput = dOutput;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }


    public boolean isIsopen() {
        return isopen;
    }

    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }

    @Override
    public String toString() {
        return "Message [weight=" + weight + ", dInput=" + dInput + ", dOutput=" + dOutput + ", isChanged=" + isChanged
                + ", isopen=" + isopen + "]";
    }

    //��������ģʽ
    private static Message instance = new Message();

    public Message() {
        super();
    };


    public static Message getinstance() {
        if (instance!=null) {
            return instance;

        } else {
            return new Message();

        }

    }


}

package org.example.iot.serial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class SerialCom extends Subject {
    private SerialPort serialPort;
    private boolean isopen = false;


    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public boolean isIsopen() {
        return isopen;
    }

    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }

    //����ģʽ
    private static Message instance = new Message();

    public SerialCom() {
    }

    public static Message getinstance() {
        if (instance != null) {
            return instance;

        } else {
            return new Message();
        }
    }

    //�������п��ô���
    public static List<String> listPorts() {
        List<String> portNameList = new ArrayList<>();
        Set<String> set = new HashSet<>();

        SerialPort[] serialPorts = SerialPort.getCommPorts();
        for (SerialPort serialPort : serialPorts) {
            if (set.add(serialPort.getSystemPortName()))
                portNameList.add(serialPort.getSystemPortName());
        }

        return portNameList;
    }

    //��ָ������
    public void openPort(SerialParameter parameter) {
        Message message = Message.getinstance();

        serialPort = SerialPort.getCommPort(parameter.getSerialPort());
        int baudrate = Integer.parseInt(parameter.getBaudRate());

        if (baudrate == 0) {
            System.out.println("baudrate is 0!!!");

        }
        if (serialPort.isOpen()) {
            System.out.println("COM Port is in use!!!");

        } else {
            serialPort.openPort(1000);
            serialPort.setFlowControl(serialPort.FLOW_CONTROL_DISABLED);
            serialPort.setComPortParameters(baudrate, 8, SerialPort.TWO_STOP_BITS, SerialPort.NO_PARITY);
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 1000, 1000);

            isopen = true;
            message.setIsopen(true);
            notifyObserverUpdate(message);

        }

        serialPort.addDataListener(new SerialReader());


    }

    private class SerialReader implements SerialPortDataListener {

        @Override
        public int getListeningEvents() {
            // TODO Auto-generated method stub
            return serialPort.LISTENING_EVENT_DATA_RECEIVED;
        }

        @Override
        public void serialEvent(SerialPortEvent arg0) {
            // TODO Auto-generated method stub
            byte[] newData = arg0.getReceivedData();

//			System.out.println(newData);
//			System.out.println(ByteUtils.bytesToHexString(newData));
//			System.out.println( new String(newData));

            //�ж��Ƿ�Ϊ�������豸��0x01��
            if (newData[0] != (byte) 0x01) return;
            //�ж�ָ��
            if (newData[1] == (byte) 0x41) {
                //���������message
                Message message = SerialProtocol.getState(newData, newData.length);
//				System.out.println(message);

                notifyObserverUpdate(message);
            }

        }


    }

    public boolean closePort() {
        Message message = Message.getinstance();
        if (isopen) {
            serialPort.closePort();

            message.setIsopen(false);
            notifyObserverUpdate(message);
            System.out.println(isopen);

        }
        return true;
    }


    public void sendCom(byte[] cmd) {
        if (!isopen) {
            return;

        }
        serialPort.writeBytes(cmd, cmd.length);

    }


}


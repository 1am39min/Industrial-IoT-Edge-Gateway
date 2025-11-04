package org.example.iot.ui;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.example.iot.database.OperateRecorder;
import org.example.iot.serial.Message;
import org.example.iot.serial.SerialCom;
import org.example.iot.serial.SerialComListener;
import org.example.iot.serial.SerialProtocol;
import org.example.iot.util.SwingUtil;
import org.example.mqttClient.*;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;

public class IOView extends JPanel implements SerialComListener, RPCListener {
    private Icon greenIcon = SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/circle_green.png"), true);
    private Icon redIcon = SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/circle_red.png"), true);
    private Icon grayIcon = SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/circle_grey.png"), true);

    private List<JLabel> inputedLEDList = new ArrayList<>();
    private List<JButton> outputButtonList = new ArrayList<>();

    private SerialCom serialCom;
    private Gson gson;

    /**
     * Create the panel.
     */
    public IOView(SerialCom serialCom) throws MqttException {
        gson = new Gson();
        setLayout(null);
        this.serialCom = serialCom;

        JLabel lbl_input = new JLabel("输入：");
        lbl_input.setFont(new Font("����", Font.BOLD, 24));
        lbl_input.setBounds(48, 35, 92, 43);
        add(lbl_input);

        JLabel lbl_output = new JLabel("输出：");
        lbl_output.setFont(new Font("����", Font.BOLD, 24));
        lbl_output.setBounds(48, 108, 92, 43);
        add(lbl_output);

        for (int i = 0; i < 4; i++) {
            JLabel inputLED = new JLabel("");
            inputLED.setIcon(grayIcon);
            inputLED.setBounds(492 - 100 * i, 26, 60, 60);
            add(inputLED);
            inputedLEDList.add(inputLED);

        }

        for (int i = 0; i < 4; i++) {
            JButton outputButton = new JButton("");
            outputButton.setIcon(grayIcon);
            outputButton.setBounds(492 - 100 * i, 102, 60, 60);
            add(outputButton);
            outputButtonList.add(outputButton);
            outputButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    //当前按钮的序号
                    int j = outputButtonList.indexOf(outputButton);
                    //当前按钮按下之前的状态
                    boolean state = Message.getinstance().getdOutput().get(j);

                    byte[] sendCmd = SerialProtocol.getDouputCmd(j, state);//转化为指令码
                    serialCom.sendCom(sendCmd);//下发指令

                    if ((j == 3) && (state == false)) {
                        OperateRecorder operateRecorder = OperateRecorder.getinstance();
                        Message message = Message.getinstance();
                        operateRecorder.setBeginWeight(message.getWeight());

                    }
                }
            });
        }

    }

    @Override
    public void update(Message message) {

        //System.out.println("IOView");
        // TODO Auto-generated method stub

        Map inMap = message.getdInput();
        if (inMap.isEmpty()) return;

        for (int i = 0; i < 4; i++) {
            if (message.getdInput().get(i)) {
                inputedLEDList.get(i).setIcon(greenIcon);

            } else {
                inputedLEDList.get(i).setIcon(redIcon);
            }

        }

        for (int i = 0; i < 4; i++) {
            if (message.getdOutput().get(i)) {
                outputButtonList.get(i).setIcon(greenIcon);

            } else {
                outputButtonList.get(i).setIcon(grayIcon);
            }
        }
    }


    @Override
    public void updateRPC(MqttMessage message) {
        String rpcMessage = new String(message.getPayload());
        if (rpcMessage.contains("setOutput")) {
            IoRPCMessage ioRPCMessage = gson.fromJson(rpcMessage, IoRPCMessage.class);
            String methodData = ioRPCMessage.getMethod().replace("setOutput", "");
            int j = Integer.parseInt(methodData);
            byte[] sendCmd = SerialProtocol.getDouputCmd(j, ioRPCMessage.isParams());//转化为指令码
            serialCom.sendCom(sendCmd);//下发指令
        }

    }
}

package org.example.iot.ui;

import com.intellij.uiDesigner.core.GridLayoutManager;
import org.example.iot.serial.Message;
import org.example.iot.serial.SerialCom;
import org.example.iot.serial.SerialComListener;
import org.example.iot.serial.SerialParameter;
import org.example.iot.util.ByteUtils;
import org.example.iot.util.SwingUtil;

import javax.swing.JPanel;


import javax.swing.JButton;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Icon;


public class ToolView extends JPanel implements SerialComListener {

    private SerialCom serialCom;
    private SerialParameter serialParameter;
    public static JButton btn_connect;

    private Icon openSeriaIcon = SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/connect.png"), true);
    private Icon closeSeriaIcon = SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/unconn.png"), true);

    /**
     * Create the panel.
     */
    public ToolView(SerialCom serialCom, SerialParameter serialParameter) {

        this.serialCom = serialCom;
        this.serialParameter = serialParameter;

        setBackground(new Color(223, 223, 223));
        setLayout(null);

        btn_connect = new JButton("串口连接");
        btn_connect.setBackground(new Color(236, 248, 255));
        btn_connect.setIcon(SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/unconn.png"), true));
        btn_connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (serialCom.isIsopen()) {
                    serialCom.closePort();
//					btn_connect.setIcon(SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/unconn.png"), true));

                } else {
                    serialCom.openPort(serialParameter);
                    byte[] bb = ByteUtils.hexToByteArray("0141C010");
                    serialCom.sendCom(bb);
//					btn_connect.setIcon(SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/connect.png"), true));


                }

            }
        });
//		btn_connect.setIcon(new ImageIcon(ToolView.class.getResource("/images/unconn.png")));
        btn_connect.setToolTipText("串口连接");
        btn_connect.setBounds(53, 10, 60, 60);
        add(btn_connect);

        JButton btn_serial = new JButton("\u4E32\u53E3\u8BBE\u7F6E");
        btn_serial.setBackground(new Color(236, 248, 255));
//		btn_serial.setIcon(new ImageIcon(ToolView.class.getResource("/images/configure_pressed.png")));
        btn_serial.setIcon(SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/configure_pressed.png"), true));
        btn_serial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SerialConfig serialConfig = new SerialConfig();
                serialConfig.setVisible(true);
            }
        });
        btn_serial.setBounds(193, 10, 60, 60);
        add(btn_serial);

        JButton btn_calibration = new JButton("校准");
        btn_calibration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalibrationView calibrationView = new CalibrationView(serialCom);
                calibrationView.setVisible(true);
            }
        });
        btn_calibration.setBackground(new Color(236, 248, 255));
//		btn_calibration.setIcon(new ImageIcon(ToolView.class.getResource("/images/appointment-new-2.png")));
        btn_calibration.setIcon(SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/appointment-new-2.png"), true));
        btn_calibration.setToolTipText("称量数据的校准");
        btn_calibration.setBounds(333, 10, 60, 60);
        add(btn_calibration);

        JButton btn_information = new JButton("系统信息");
        btn_information.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                informationView about = new informationView();
                about.setVisible(true);
            }
        });
        btn_information.setBackground(new Color(236, 248, 255));
//		btn_information.setIcon(new ImageIcon(ToolView.class.getResource("/images/Alert_15.png")));
        btn_information.setIcon(SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/Alert_15.png"), true));
        btn_information.setToolTipText("显示版本信息");
        btn_information.setBounds(473, 10, 60, 60);
        add(btn_information);

        JButton btn_exit = new JButton("退出");
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (serialCom.isIsopen()) {
                    serialCom.closePort();
                }
                System.exit(0);
            }
        });

        btn_exit.setBackground(new Color(236, 248, 255));
//		btn_exit.setIcon(new ImageIcon(ToolView.class.getResource("/images/exit_normal.png")));
        btn_exit.setIcon(SwingUtil.createAutoAdjustIcon(ToolView.class.getResource("/images/exit_normal.png"), true));
        btn_exit.setToolTipText("系统退出");
        btn_exit.setBounds(613, 10, 60, 60);
        add(btn_exit);

    }

    @Override
    public void update(Message message) {
        // TODO Auto-generated method stub
        if (message.isIsopen()) {
            btn_connect.setIcon(openSeriaIcon);

        } else {
            btn_connect.setIcon(closeSeriaIcon);

        }

    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }
}

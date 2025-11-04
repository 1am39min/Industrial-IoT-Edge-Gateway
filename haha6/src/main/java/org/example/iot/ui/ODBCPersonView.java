package org.example.iot.ui;

import com.google.gson.Gson;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.example.mqttClient.RPCListener;
import org.example.mqttClient.RPC_ODBC_Result;
import org.example.mqttClient.Rpc_Odbc_Method;
import org.example.mqttClient.TBGWMqttClient;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ODBCPersonView extends JPanel implements RPCListener {

    private JTextField idTextField;
    private JLabel IDLabel;
    private JLabel ageLabel;
    private JTextField ageTextField;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton query_button;
    private JButton insert_button;
    private JTextField sexTextField;
    private Gson gson;
    JPanel panel;

    private TBGWMqttClient tbgmqttClient;


    public ODBCPersonView(TBGWMqttClient tbgwMqttClient) {
        gson = new Gson();
        this.tbgmqttClient = tbgwMqttClient;
        setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        setEnabled(true);
        IDLabel = new JLabel();
        IDLabel.setText("编号：");
        add(IDLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 40), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("性别：");
        add(label1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 40), null, 0, false));
        ageLabel = new JLabel();
        ageLabel.setText("年龄：");
        add(ageLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 40), null, 0, false));
        query_button = new JButton();
        query_button.setText("查询");
        add(query_button, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        insert_button = new JButton();
        insert_button.setText("插入");
        add(insert_button, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        sexTextField = new JTextField();
        add(sexTextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 40), null, 0, false));
        idTextField = new JTextField();
        add(idTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 40), null, 0, false));
        ageTextField = new JTextField();
        add(ageTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 40), null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setText("姓名：");
        add(nameLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 40), null, 0, false));
        nameTextField = new JTextField();
        add(nameTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 40), null, 0, false));

        query_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = idTextField.getText().trim();
                Rpc_Odbc_Method rpcOdbcMethod = new Rpc_Odbc_Method();
                rpcOdbcMethod.setMethod("rpcQuery");
                List<String> args = new ArrayList<>();
                args.add(idString);
                Map<String, List<String>> params = new HashMap<>();
                params.put("args", args);
                rpcOdbcMethod.setParams(params);
                Gson gson = new Gson();
                System.out.println(gson.toJson(rpcOdbcMethod));

                tbgmqttClient.publish("v1/devices/me/rpc/request/1", gson.toJson(rpcOdbcMethod));


            }
        });


        insert_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = idTextField.getText().trim();
                String name = nameTextField.getText().trim();
                String age = ageTextField.getText().trim();
                String sex = sexTextField.getText().trim();

                Rpc_Odbc_Method rpcOdbcMethod = new Rpc_Odbc_Method();
                rpcOdbcMethod.setMethod("rpcInsert");

                List<String> args = new ArrayList<>();
                args.add(idString);
                args.add(name);
                args.add(age);
                args.add(sex);

                Map<String, List<String>> params = new HashMap<>();
                params.put("args", args);

                rpcOdbcMethod.setParams(params);
                //
                Gson gson = new Gson();
                System.out.println(gson.toJson(rpcOdbcMethod));

                tbgmqttClient.publish("v1/devices/me/rpc/request/1", gson.toJson(rpcOdbcMethod));

            }
        });
    }

    @Override
    public void updateRPC(MqttMessage message) {
        String rpcMessage = new String(message.getPayload());
        if (rpcMessage.contains("rpcQuery")) {
            RPC_ODBC_Result odbcResult = gson.fromJson(rpcMessage, RPC_ODBC_Result.class);
            idTextField.setText(String.valueOf(odbcResult.getParams().getV_id()));
            nameTextField.setText(odbcResult.getParams().getV_name());
            ageTextField.setText(odbcResult.getParams().getV_age());
            sexTextField.setText(odbcResult.getParams().getV_sex());

        } else if (rpcMessage.contains("rpcInsert")) {
            RPC_ODBC_Result result = gson.fromJson(rpcMessage, RPC_ODBC_Result.class);
            if (result.getParams().isSuccess()) {
                JOptionPane.showConfirmDialog(insert_button, "插入成功");
            } else {
                JOptionPane.showConfirmDialog(insert_button, "插入失败");
            }

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
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel.setEnabled(true);
        IDLabel = new JLabel();
        Font IDLabelFont = this.$$$getFont$$$(null, -1, 16, IDLabel.getFont());
        if (IDLabelFont != null) IDLabel.setFont(IDLabelFont);
        IDLabel.setText("编号：");
        panel.add(IDLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 40), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("性别：");
        panel.add(label1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 40), null, 0, false));
        ageLabel = new JLabel();
        Font ageLabelFont = this.$$$getFont$$$(null, -1, 16, ageLabel.getFont());
        if (ageLabelFont != null) ageLabel.setFont(ageLabelFont);
        ageLabel.setText("年龄：");
        panel.add(ageLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 40), null, 0, false));
        query_button = new JButton();
        Font query_buttonFont = this.$$$getFont$$$(null, -1, 14, query_button.getFont());
        if (query_buttonFont != null) query_button.setFont(query_buttonFont);
        query_button.setText("查询");
        panel.add(query_button, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        insert_button = new JButton();
        Font insert_buttonFont = this.$$$getFont$$$(null, -1, 14, insert_button.getFont());
        if (insert_buttonFont != null) insert_button.setFont(insert_buttonFont);
        insert_button.setText("插入");
        panel.add(insert_button, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        sexTextField = new JTextField();
        panel.add(sexTextField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, 35), null, 0, false));
        idTextField = new JTextField();
        panel.add(idTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, 35), null, 0, false));
        ageTextField = new JTextField();
        panel.add(ageTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, 35), null, 0, false));
        nameLabel = new JLabel();
        Font nameLabelFont = this.$$$getFont$$$(null, -1, 16, nameLabel.getFont());
        if (nameLabelFont != null) nameLabel.setFont(nameLabelFont);
        nameLabel.setText("姓名：");
        panel.add(nameLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 40), null, 0, false));
        nameTextField = new JTextField();
        panel.add(nameTextField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, 35), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }


}

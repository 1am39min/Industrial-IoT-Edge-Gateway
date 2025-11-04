package org.example.mqttClient;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface RPCListener {

    void updateRPC(MqttMessage message);
}

package org.example.mqttClient;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class RPCsubject {


        private List<RPCListener> clientList = new ArrayList<>();

        public void resisterObserver(RPCListener listener) {
            clientList.add((RPCListener) listener);


        }

        public void unresgisterObsever(RPCListener listener) {
            if((clientList != null) && (clientList.contains(listener))) {
                clientList.remove(listener);
            }
        }

        public void notifyObserverUpdate(MqttMessage message) {
            for(RPCListener listener:clientList) {
                listener.updateRPC(message);
            }
            //System.out.println(message);
        }





}

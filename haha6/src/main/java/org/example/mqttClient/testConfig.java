package org.example.mqttClient;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "test")
@XmlType(propOrder = {"broker","username","password","clientID","topic","qos"})
public class testConfig {
    private String broker;
    private String username;
    private String password;
    private String clientID;
    private String topic;
    private int qos;


    public testConfig() {
    }

    public testConfig(String broker, String username, String password, String clientID, String topic, int qos) {
        this.broker = broker;
        this.username = username;
        this.password = password;
        this.clientID = clientID;
        this.topic = topic;
        this.qos = qos;
    }

    /**
     * 获取
     * @return broker
     */
    public String getBroker() {
        return broker;
    }

    /**
     * 设置
     * @param broker
     */
    public void setBroker(String broker) {
        this.broker = broker;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return clientID
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * 设置
     * @param clientID
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * 获取
     * @return topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置
     * @param topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 获取
     * @return qos
     */
    public int getQos() {
        return qos;
    }

    /**
     * 设置
     * @param qos
     */
    public void setQos(int qos) {
        this.qos = qos;
    }

    public String toString() {
        return "testConfig{broker = " + broker + ", username = " + username + ", password = " + password + ", clientID = " + clientID + ", topic = " + topic + ", qos = " + qos + "}";
    }
}

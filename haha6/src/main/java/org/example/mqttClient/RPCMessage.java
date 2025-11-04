package org.example.mqttClient;

public class RPCMessage {
    private int pin;
    private boolean enabled;

    public RPCMessage() {
    }

    public RPCMessage(int pin, boolean enabled) {
        this.pin = pin;
        this.enabled = enabled;
    }

    /**
     * 获取
     * @return pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * 设置
     * @param pin
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * 获取
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 设置
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String toString() {
        return "RPCMessage{pin = " + pin + ", enabled = " + enabled + "}";
    }
}

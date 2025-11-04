package org.example.mqttClient;

public class IoRPCMessage {
    private String method;
    private boolean params;

    public IoRPCMessage() {
    }

    public IoRPCMessage(String method, boolean params) {
        this.method = method;
        this.params = params;
    }

    /**
     * 获取
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取
     * @return params
     */
    public boolean isParams() {
        return params;
    }

    /**
     * 设置
     * @param params
     */
    public void setParams(boolean params) {
        this.params = params;
    }

    public String toString() {
        return "IoRPCMessage{method = " + method + ", params = " + params + "}";
    }
}

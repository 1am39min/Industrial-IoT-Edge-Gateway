package org.example.mqttClient;

public class RPC_ODBC_Result {
    private String methodString;
    private Params params;

    public RPC_ODBC_Result() {
    }

    public RPC_ODBC_Result(String methodString, Params params) {
        this.methodString = methodString;
        this.params = params;
    }

    /**
     * 获取
     * @return methodString
     */
    public String getMethodString() {
        return methodString;
    }

    /**
     * 设置
     * @param methodString
     */
    public void setMethodString(String methodString) {
        this.methodString = methodString;
    }

    /**
     * 获取
     * @return params
     */
    public Params getParams() {
        return params;
    }

    /**
     * 设置
     * @param params
     */
    public void setParams(Params params) {
        this.params = params;
    }

    public String toString() {
        return "RPC_ODBC_Result{methodString = " + methodString + ", params = " + params + "}";
    }
}

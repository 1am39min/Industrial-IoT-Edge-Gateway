package org.example.mqttClient;

import java.util.List;
import java.util.Map;

public class Rpc_Odbc_Method {
    private String method;
    private Map<String, List<String>> params;

    public Rpc_Odbc_Method() {
    }

    public Rpc_Odbc_Method(String method, Map<String, List<String>> params) {
        this.method = method;
        this.params = params;
    }

    /**
     * 获取
     * @return methods
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
    public Map<String, List<String>> getParams() {
        return params;
    }

    /**
     * 设置
     * @param params
     */
    public void setParams(Map<String, List<String>> params) {
        this.params = params;
    }

    public String toString() {
        return "Rpc_Odbc_Method{methods = " + method + ", params = " + params + "}";
    }
}

package org.example.mqttClient;

public class Params {
    private int v_no;
    private String v_id;
    private String v_name;
    private  String v_age;
    private String v_sex;
    private boolean success;

    public Params() {
    }

    public Params(int v_no, String v_id, String v_name, String v_age, String v_sex, boolean success) {
        this.v_no = v_no;
        this.v_id = v_id;
        this.v_name = v_name;
        this.v_age = v_age;
        this.v_sex = v_sex;
        this.success = success;
    }

    /**
     * 获取
     * @return v_no
     */
    public int getV_no() {
        return v_no;
    }

    /**
     * 设置
     * @param v_no
     */
    public void setV_no(int v_no) {
        this.v_no = v_no;
    }

    /**
     * 获取
     * @return v_id
     */
    public String getV_id() {
        return v_id;
    }

    /**
     * 设置
     * @param v_id
     */
    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    /**
     * 获取
     * @return v_name
     */
    public String getV_name() {
        return v_name;
    }

    /**
     * 设置
     * @param v_name
     */
    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    /**
     * 获取
     * @return v_age
     */
    public String getV_age() {
        return v_age;
    }

    /**
     * 设置
     * @param v_age
     */
    public void setV_age(String v_age) {
        this.v_age = v_age;
    }

    /**
     * 获取
     * @return v_sex
     */
    public String getV_sex() {
        return v_sex;
    }

    /**
     * 设置
     * @param v_sex
     */
    public void setV_sex(String v_sex) {
        this.v_sex = v_sex;
    }

    /**
     * 获取
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toString() {
        return "Params{v_no = " + v_no + ", v_id = " + v_id + ", v_name = " + v_name + ", v_age = " + v_age + ", v_sex = " + v_sex + ", success = " + success + "}";
    }
}

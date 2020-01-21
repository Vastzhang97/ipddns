/**
 * Copyright 2019 bejson.com
 */
package per.vastzhang.piddns.pojo;

/**
 * Auto-generated: 2019-04-05 20:22:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AlterResultBean {

    private int code;
    private String message;
    private String codeDesc;
    private Data data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "AlterResultBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", codeDesc='" + codeDesc + '\'' +
                ", data=" + data +
                '}';
    }
}
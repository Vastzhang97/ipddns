/**
 * Copyright 2019 bejson.com
 */
package per.vastzhang.piddns.pojo;

import java.util.Date;

/**
 * Auto-generated: 2019-04-05 16:38:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Records {

    private long id;
    private int ttl;
    private String value;
    private int enabled;
    private String status;
    private Date updated_on;
    private int q_project_id;
    private String name;
    private String line;
    private String line_id;
    private String type;
    private String remark;
    private int mx;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getTtl() {
        return ttl;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public void setQ_project_id(int q_project_id) {
        this.q_project_id = q_project_id;
    }

    public int getQ_project_id() {
        return q_project_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getLine_id() {
        return line_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setMx(int mx) {
        this.mx = mx;
    }

    public int getMx() {
        return mx;
    }

    @Override
    public String toString() {
        return "Records{" +
                "id=" + id +
                ", ttl=" + ttl +
                ", value='" + value + '\'' +
                ", enabled=" + enabled +
                ", status='" + status + '\'' +
                ", updated_on=" + updated_on +
                ", q_project_id=" + q_project_id +
                ", name='" + name + '\'' +
                ", line='" + line + '\'' +
                ", line_id='" + line_id + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", mx=" + mx +
                '}';
    }
}
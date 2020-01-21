/**
 * Copyright 2019 bejson.com
 */
package per.vastzhang.piddns.pojo;

/**
 * Auto-generated: 2019-04-05 16:38:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Info {

    private String sub_domains;
    private String record_total;
    private String records_num;

    public void setSub_domains(String sub_domains) {
        this.sub_domains = sub_domains;
    }

    public String getSub_domains() {
        return sub_domains;
    }

    public void setRecord_total(String record_total) {
        this.record_total = record_total;
    }

    public String getRecord_total() {
        return record_total;
    }

    public void setRecords_num(String records_num) {
        this.records_num = records_num;
    }

    public String getRecords_num() {
        return records_num;
    }

    @Override
    public String toString() {
        return "Info{" +
                "sub_domains='" + sub_domains + '\'' +
                ", record_total='" + record_total + '\'' +
                ", records_num='" + records_num + '\'' +
                '}';
    }
}
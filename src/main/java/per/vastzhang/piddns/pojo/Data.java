/**
 * Copyright 2019 bejson.com
 */
package per.vastzhang.piddns.pojo;

import java.util.List;

/**
 * Auto-generated: 2019-04-05 16:38:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private Domain domain;
    private Info info;
    private List<Records> records;
    private Record record;

    public void setRecord(Record record) {
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Info getInfo() {
        return info;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }

    public List<Records> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        return "Data{" +
                "domain=" + domain +
                ", info=" + info +
                ", records=" + records +
                ", record=" + record +
                '}';
    }
}
package com.example.asexperiment_end.Bean;

import com.example.asexperiment_end.Utils.DateUtil;

import java.util.Date;
import java.util.UUID;

public class RecordBean {

    //定义类型的枚举类型
    public enum RecordType{
        RECORD_TYPE_EXPENSE,RECORD_TYPE_INCOME
    }

    public static String TAG = "RecordBean";


    private String uuid;
    private Integer id;
    private Double money;
    //记录是进账还是出账
    private RecordType type;
    private String remark;
    private String category;
    private String date;



    private long timesstamp;

    public RecordBean() {
        this.uuid = UUID.randomUUID().toString();
        this.date = DateUtil.getFormattedDate();
        timesstamp = System.currentTimeMillis();
    }
    public long getTimesstamp() {
        return timesstamp;
    }

    public void setTimesstamp(long timesstamp) {
        this.timesstamp = timesstamp;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getType() {
        if (this.type == RecordType.RECORD_TYPE_EXPENSE){
            return 1;
        }else {
            return 2;
        }
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RecordBean{" +
                "uuid='" + uuid + '\'' +
                ", id=" + id +
                ", money=" + money +
                ", type=" + type +
                ", remark='" + remark + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", timesstamp=" + timesstamp +
                '}';
    }
}

package com.cdyoue.oddJobs.en;

import com.cdyoue.oddJobs.constant.DataTypeConstant;

import java.util.Date;

/**
 * Created by cdyoue on 2017/2/24.
 */
public enum DataTypeEnum {
    INTEGER(Integer.class, DataTypeConstant.INTEGER),
    LONG(Long.class,DataTypeConstant.LONG),
    DOUBLE(Double.class, DataTypeConstant.DOUBLE),
    STRING(String.class,DataTypeConstant.STRING),
    DATE(Date.class,DataTypeConstant.DATE);

    private Class dataType;
    private String type;
    DataTypeEnum(Class dataType, String type) {
        this.dataType = dataType;
        this.type = type;
    }
    public static Class getTypeClass(String type) {
        if(type == null){
            return DataTypeEnum.STRING.getDataType();
        }
        for (DataTypeEnum oct : DataTypeEnum.values()) {
            if (oct.getType().equals(type)) {
                return oct.getDataType();
            }
        }
        return DataTypeEnum.STRING.getDataType();
    }

    public Class getDataType() {
        return dataType;
    }

    public void setDataType(Class dataType) {
        this.dataType = dataType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

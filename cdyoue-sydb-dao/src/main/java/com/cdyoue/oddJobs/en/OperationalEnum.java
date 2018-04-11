package com.cdyoue.oddJobs.en;

/**
 * Created by 40864 on 2017/3/4.
 */
public enum OperationalEnum {
    //等于
    EQ("equal",1),

    //不相等
    NEQ("notEqual",2),

    //大于
    GT("greaterThan", 3),
    //小于
    LT("lessThan", 4);

    private String operational;
    private Integer code;
    OperationalEnum(String operational, Integer code) {
        this.operational = operational;
        this.code = code;
    }
    public static String getOperational(Integer code) {
        if(code == null){
            return OperationalEnum.EQ.getOperational();
        }
        for (OperationalEnum oct : OperationalEnum.values()) {
            if (oct.getCode().equals(code)) {
                return oct.getOperational();
            }
        }
        return OperationalEnum.EQ.getOperational();
    }

    private String getOperational() {
        return operational;
    }

    private void setOperational(String operational) {
        this.operational = operational;
    }

    private Integer getCode() {
        return code;
    }

    private void setCode(Integer code) {
        this.code = code;
    }
}

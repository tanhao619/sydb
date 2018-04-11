package com.cdyoue.oddJobs.spec;

import com.cdyoue.oddJobs.constant.DataTypeConstant;

/**
 * Created by liaoyoule on 2017/4/21.
 */
public class QueryRequest {
    private String f; //字段
    private String v;   //值
    private String t = DataTypeConstant.STRING;//类型
    private Operator o = Operator.EQ;//操作

    private Boolean andOr = true;

    public QueryRequest(String f, String v) {
        this.f = f;
        this.v = v;
    }

    public QueryRequest() {
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public Operator getO() {
        return o;
    }

    public void setO(Operator o) {
        this.o = o;
    }

    public Boolean getAndOr() {
        return andOr;
    }

    public void setAndOr(Boolean andOr) {
        this.andOr = andOr;
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;

        QueryRequest that = (QueryRequest) o1;

        if (f != null ? !f.equals(that.f) : that.f != null) return false;
        if (v != null ? !v.equals(that.v) : that.v != null) return false;
        if (t != null ? !t.equals(that.t) : that.t != null) return false;
        if (o != null ? !o.equals(that.o) : that.o != null) return false;
        return andOr != null ? andOr.equals(that.andOr) : that.andOr == null;
    }

    @Override
    public int hashCode() {
        int result = f != null ? f.hashCode() : 0;
        result = 31 * result + (v != null ? v.hashCode() : 0);
        result = 31 * result + (t != null ? t.hashCode() : 0);
        result = 31 * result + (o != null ? o.hashCode() : 0);
        result = 31 * result + (andOr != null ? andOr.hashCode() : 0);
        return result;
    }
}

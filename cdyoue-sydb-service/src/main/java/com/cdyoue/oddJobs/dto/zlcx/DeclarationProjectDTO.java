package com.cdyoue.oddJobs.dto.zlcx;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by sky on 2017/9/18.
 */
public class DeclarationProjectDTO {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

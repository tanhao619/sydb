package com.cdyoue.oddJobs.dto.xqdt;

import com.cdyoue.oddJobs.dto.common.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by tr on 2017/6/6.
 */
public class ProjectNews {
    @JsonProperty("enterName")
    private String enterName;

    @JsonProperty("requireName")
    private String requireName;

    @JsonProperty("finance")
    private BigDecimal finance;

    @JsonProperty("category")
    private Category category;

    @ApiModelProperty(value = "公司名称")
    public String getEnterName() {
        return enterName;
    }

    public void setEnterName(String enterName) {
        this.enterName = enterName;
    }

    @ApiModelProperty(value = "需求名称")
    public String getRequireName() {
        return requireName;
    }

    public void setRequireName(String requireName) {
        this.requireName = requireName;
    }

    @ApiModelProperty(value = "预算")
    public BigDecimal getFinance() {
        return finance;
    }

    public void setFinance(BigDecimal finance) {
        this.finance = finance;
    }

    @ApiModelProperty(value = "需求分类ID")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

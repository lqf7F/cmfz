package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Banner implements Serializable {
    @ExcelIgnore
    private String id;
    @Excel(name = "标题", needMerge = true)
    private String title;
    @Excel(name = "描述", needMerge = true)
    private String description;
    @Excel(name = "时间", needMerge = true, format = "yyyy-MM-dd")
    private Date time;
    @Excel(name = "状态", needMerge = true)
    private String state;
    @Excel(name = "图片", type = 2, width = 50, height = 35, needMerge = true)
    private String img;
}

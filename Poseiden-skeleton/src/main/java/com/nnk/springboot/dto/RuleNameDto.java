package com.nnk.springboot.dto;

import lombok.Data;

@Data
public class RuleNameDto {

    public RuleNameDto(int id, String name, String description, String json, String template, String sqlStr,
            String sqlPart) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlPart = sqlPart;
        this.sqlStr = sqlStr;
    }

    private int id;
    private String name;
    private String description;
    private String json;
    private String template;
    private String sqlStr;
    private String sqlPart;
}

package com.nnk.springboot.web.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * This class is the DTO class for the RuleName Entity
 * 
 * @author Rémi Deronzier
 */
@Data
public class RuleNameDto {

    public RuleNameDto(Integer id, String name, String description, String json, String template, String sqlStr,
            String sqlPart) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlPart = sqlPart;
        this.sqlStr = sqlStr;
    }

    private Integer id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotBlank(message = "Json must not be blank")
    private String json;

    @NotBlank(message = "Template must not be blank")
    private String template;

    @NotBlank(message = "Sql str must not be blank")
    private String sqlStr;

    @NotBlank(message = "Sql part must not be blank")
    private String sqlPart;

}

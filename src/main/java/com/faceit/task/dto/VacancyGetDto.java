package com.faceit.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacancyGetDto {
    private Long id;
    private String slug;
    private String company_name;
    private String title;
    private String description;
    private boolean remote;
    private String url;
    private List<String> tags;
    private List<String> job_types;
    private String location;
    private Long created_at;
}


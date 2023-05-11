package com.faceit.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private Long id;
    private String name;
    private String title;
    private String companyName;
    private boolean isRemote;
    private String url;
    private LocalDate date;
    private String city;
    private String seniority;
}

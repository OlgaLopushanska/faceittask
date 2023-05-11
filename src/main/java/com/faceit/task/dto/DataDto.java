package com.faceit.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataDto {
    private LinkedList<VacancyGetDto> data;
    private LinkDto links;
    private MetaDto meta;

}

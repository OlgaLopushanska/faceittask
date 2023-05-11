package com.faceit.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetaDto {
    private int current_page;
    private int from;
    private String path;
    private int per_page;
    private int to;
    private String terms;
    private String info;
}

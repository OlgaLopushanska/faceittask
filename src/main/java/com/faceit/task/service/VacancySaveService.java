package com.faceit.task.service;

import com.faceit.task.dto.VacancyDto;
import com.faceit.task.entity.Vacancy;

import java.util.List;

public interface VacancySaveService {
    public List<Vacancy> saveVacancies();

    public List<Vacancy> uploadNewVacancies();
}

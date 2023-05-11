package com.faceit.task.service;

import com.faceit.task.dto.CityCountDto;
import com.faceit.task.dto.VacancyDto;
import com.faceit.task.entity.Vacancy;

import java.util.List;

public interface VacancyService {
     public List<VacancyDto> get10Vacancies();

     public List<CityCountDto> getVacanciesNumberForCities();

     public List<VacancyDto> getVacanciesPerPage(int page, int vacancy);
}

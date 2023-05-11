package com.faceit.task.repository;

import com.faceit.task.dto.CityCount;
import com.faceit.task.dto.CityCountDto;
import com.faceit.task.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("SELECT new com.faceit.task.dto.CityCount(v.city, COUNT(v.city)) FROM Vacancy AS v GROUP BY v.city")
    List<CityCount> getVacanciesNumberForCities();
}

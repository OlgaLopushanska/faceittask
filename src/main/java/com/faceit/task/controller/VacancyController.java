package com.faceit.task.controller;

import com.faceit.task.dto.CityCountDto;
import com.faceit.task.dto.VacancyDto;
import com.faceit.task.entity.Vacancy;
import com.faceit.task.service.VacancyService;
import com.faceit.task.service.impl.VacancyServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/vacancies")
public class VacancyController {
    private VacancyService vacancyService;

    @GetMapping("/10relevant")
    public ResponseEntity <List<VacancyDto>> getVacancies() {
        List<VacancyDto> vacancyList = vacancyService.get10Vacancies();
        return new ResponseEntity<>(vacancyList, HttpStatus.OK);
    }

    @GetMapping("/vacancyCountForCities")
    public ResponseEntity<List<CityCountDto>> getVacanciesCountForCities() {
        List<CityCountDto> cityCountDtos = vacancyService.getVacanciesNumberForCities();
        return new ResponseEntity<>(cityCountDtos, HttpStatus.OK);
    }
    //Vacancies are sorted by slug(name) and you can choose vacancy number per page and page itself
    @GetMapping("/page={page}/vacancy={vacancy}")
    public ResponseEntity<List<VacancyDto>> getVacanciesPerPage(@PathVariable("page") int page, @PathVariable("vacancy") int vacancy) {
        List<VacancyDto> vacancyDtos = vacancyService.getVacanciesPerPage(page, vacancy);
        return new ResponseEntity<>(vacancyDtos, HttpStatus.OK);
    }

    public VacancyController(VacancyServiceImpl vacancyService) {
        this.vacancyService = vacancyService;
    }
}

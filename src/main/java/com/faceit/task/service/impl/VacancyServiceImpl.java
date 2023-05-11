package com.faceit.task.service.impl;

import com.faceit.task.dto.*;
import com.faceit.task.entity.Vacancy;
import com.faceit.task.mapper.VacancyMapper;
import com.faceit.task.repository.VacancyRepository;
import com.faceit.task.service.VacancySaveService;
import com.faceit.task.service.VacancyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyServiceImpl implements VacancyService, VacancySaveService {
    private VacancyRepository vacancyRepository;
    private WebClient webClient;
    @Value("${link}")
    private String link;
    @Override
    public List<VacancyDto> get10Vacancies() {
        List<Vacancy> vacancyList = vacancyRepository.findAll();
        List<VacancyDto> vacancyDtos = vacancyList.stream()
                .filter(e->e.getRank() <= 10)
                .limit(10)
                .map(VacancyMapper::mapToVacancyDto)
                .limit(10)
                .collect(Collectors.toList());
        return vacancyDtos;
    }

    @Override
    public List<CityCountDto> getVacanciesNumberForCities() {
        List<CityCount> cityCount = vacancyRepository.getVacanciesNumberForCities();
        List<CityCountDto> cityCountDtos = cityCount.stream()
                .map(VacancyMapper::mapToCityCountDto)
                .collect(Collectors.toList());
        return cityCountDtos;
    }

    @Override
    public List<VacancyDto> getVacanciesPerPage(int page, int vacancy) {
        Pageable pageable = PageRequest.of(page+1, vacancy, Sort.by("name"));
        List<Vacancy> vacancyList = vacancyRepository.findAll(pageable).getContent();
        List<VacancyDto> vacancyDtos = vacancyList.stream()
                .map(VacancyMapper::mapToVacancyDto)
                .collect(Collectors.toList());
        return vacancyDtos;
    }


    public VacancyServiceImpl(VacancyRepository vacancyRepository, WebClient webClient) {
        this.vacancyRepository = vacancyRepository;
        this.webClient = webClient;
    }

    @Override
    public List<Vacancy> saveVacancies() {
        List<Vacancy> vacancyList = uploadVacanciesFromSoure();
        List<Vacancy> savedVacancy = vacancyList.stream()
                .map(e->vacancyRepository.save(e))
                .collect(Collectors.toList());
        return savedVacancy;
    }

    @Override
    public List<Vacancy> uploadNewVacancies() {
        List<Vacancy> vacancyUploadedList = uploadVacanciesFromSoure();
        List<Vacancy> vacancyListFromDB = vacancyRepository.findAll();
        for(int counter = 0; counter < vacancyUploadedList.size(); counter++) {
            if(vacancyListFromDB.contains(vacancyUploadedList.get(counter))) {
                vacancyUploadedList.remove(vacancyUploadedList.get(counter));
            }
        }
        List<Vacancy> savedVacancy = vacancyUploadedList.stream()
                .map(e->vacancyRepository.save(e))
                .collect(Collectors.toList());
        return savedVacancy;
    }

    private List<Vacancy> uploadVacanciesFromSoure() {
        List<VacancyGetDto> vacancyGetDtos = new ArrayList<>();
        for(int pageCounter = 1; pageCounter <= 5; pageCounter++) {
            Mono<DataDto> response = webClient.get()
                    .uri(link + pageCounter)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<DataDto>() {});
            List<VacancyGetDto> vacancyDto = response.block().getData();
            vacancyGetDtos.addAll(vacancyDto);
        }
        List<Vacancy> vacancyList = new ArrayList<>();
        for(int counter = 0; counter < vacancyGetDtos.size(); counter++){
            Vacancy vacancy = VacancyMapper.mapToVacancy(vacancyGetDtos.get(counter));
            vacancy.setRank(counter + 1);
            vacancyList.add(vacancy);
        }
        return  vacancyList;
    }
}

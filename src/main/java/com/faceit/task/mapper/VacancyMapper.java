package com.faceit.task.mapper;

import com.faceit.task.dto.CityCount;
import com.faceit.task.dto.CityCountDto;
import com.faceit.task.dto.VacancyDto;
import com.faceit.task.dto.VacancyGetDto;
import com.faceit.task.entity.Vacancy;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class VacancyMapper {
    public static Vacancy mapToVacancy(VacancyGetDto vacancyGetDto) {
        LocalDate date =
                LocalDate.ofInstant(Instant.ofEpochSecond(vacancyGetDto.getCreated_at()), TimeZone.getDefault().toZoneId());

        Vacancy vacancy = new Vacancy(
                vacancyGetDto.getId(), vacancyGetDto.getSlug(), vacancyGetDto.getTitle(),
                vacancyGetDto.getCompany_name(), vacancyGetDto.isRemote(), vacancyGetDto.getUrl(),
                date, vacancyGetDto.getLocation(), vacancyGetDto.getJob_types().size() > 0 ? vacancyGetDto.getJob_types().get(0) : "", 0);
        return vacancy;
    }

    public static VacancyDto mapToVacancyDto(Vacancy vacancy) {
        VacancyDto vacancyDto = new VacancyDto(
                vacancy.getId(), vacancy.getName(), vacancy.getTitle(), vacancy.getCompanyName(),
                vacancy.isRemote(), vacancy.getUrl(), vacancy.getDate(), vacancy.getCity(), vacancy.getSeniority());
        return  vacancyDto;
    }

    public static CityCountDto mapToCityCountDto(CityCount cityCount) {
        CityCountDto cityCountDto = new CityCountDto(
                cityCount.getCity(), cityCount.getCount());
        return cityCountDto;
    }
}

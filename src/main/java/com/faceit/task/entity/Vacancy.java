package com.faceit.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String companyName;
    private boolean isRemote;
    private String url;
    private LocalDate date;
    private String city;
    private String seniority;
    private int rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return isRemote == vacancy.isRemote && Objects.equals(name, vacancy.name) && Objects.equals(title, vacancy.title)
                && Objects.equals(companyName, vacancy.companyName) && Objects.equals(url, vacancy.url)
                && Objects.equals(date, vacancy.date) && Objects.equals(city, vacancy.city)
                && Objects.equals(seniority, vacancy.seniority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, companyName, isRemote, url, date, city, seniority);
    }
}

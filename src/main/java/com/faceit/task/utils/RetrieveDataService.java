package com.faceit.task.utils;

//import com.faceit.task.service.VacancyService;
import com.faceit.task.service.VacancySaveService;
import com.faceit.task.service.VacancyService;
import com.faceit.task.service.impl.VacancyServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RetrieveDataService {
    private VacancySaveService vacancyService;

    @EventListener(ApplicationReadyEvent.class)
    public void saveDataForAllSourcesForToday() throws InterruptedException {
        vacancyService.saveVacancies();
    }

    public RetrieveDataService(VacancyServiceImpl vacancyServiceImpl) {
        this.vacancyService = vacancyServiceImpl;
    }
}

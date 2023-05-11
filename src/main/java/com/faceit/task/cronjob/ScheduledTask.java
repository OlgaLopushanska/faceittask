package com.faceit.task.cronjob;

import com.faceit.task.service.VacancySaveService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private VacancySaveService vacancySaveService;

    //once in an hour
    @Scheduled(fixedRate = 3600000)
    public void uploadFreshData() {
        vacancySaveService.uploadNewVacancies();
    }
}

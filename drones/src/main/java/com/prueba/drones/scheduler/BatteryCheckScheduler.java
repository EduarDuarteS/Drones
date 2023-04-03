package com.prueba.drones.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatteryCheckScheduler {

    @Autowired
    private BatteryCheckTask batteryCheckTask;

    @Scheduled(fixedDelay = 60000) // Run every minute
    public void runBatteryCheckTask() {
        batteryCheckTask.run();
    }

}

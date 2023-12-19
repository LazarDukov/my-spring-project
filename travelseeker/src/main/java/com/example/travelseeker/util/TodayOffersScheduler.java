package com.example.travelseeker.util;

import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CarRentService;
import com.example.travelseeker.service.HotelService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TodayOffersScheduler {
    private final AirplaneTicketsService airplaneTicketsService;
    private final CarRentService carRentService;

    private final HotelService hotelService;

    public TodayOffersScheduler(AirplaneTicketsService airplaneTicketsService, CarRentService carRentService, HotelService hotelService) {
        this.airplaneTicketsService = airplaneTicketsService;
        this.carRentService = carRentService;
        this.hotelService = hotelService;
    }


    @Scheduled(cron = "* * * * * 5")
    public void startPromotions() {
        airplaneTicketsService.promotions();

    }

    @Scheduled(cron = "* * * * * 6")
    public void endPromotions() {
        airplaneTicketsService.promotionsEnd();
    }

}

package com.example.travelseeker.interceptor;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.model.entities.Hotel;
import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CarRentService;
import com.example.travelseeker.service.HotelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Configuration
public class SortOffersByPriceInterceptor implements HandlerInterceptor {
    private final AirplaneTicketsService airplaneTicketsService;
    private final CarRentService carRentService;
    private final HotelService hotelService;


    public SortOffersByPriceInterceptor(AirplaneTicketsService airplaneTicketsService, CarRentService carRentService, HotelService hotelService) {
        this.airplaneTicketsService = airplaneTicketsService;
        this.carRentService = carRentService;


        this.hotelService = hotelService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals("/")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<AirplaneTicket> airplaneTicketsSorted = airplaneTicketsService.getSortedAirplaneTickets();
        if (airplaneTicketsSorted.size() == 0) {
            airplaneTicketsSorted = Collections.emptyList();
        }
        List<CarRent> carRentsSorted = this.carRentService.getSortedCarRents();
        if (carRentsSorted.size() == 0) {
            carRentsSorted = Collections.emptyList();
        }
        List<Hotel> hotelsSorted = this.hotelService.getSortedHotels();
        if (hotelsSorted.size() == 0) {
            hotelsSorted = Collections.emptyList();
        }
        if (modelAndView != null) {
            modelAndView.addObject("airplaneTickets", airplaneTicketsSorted);
            modelAndView.addObject("cars", carRentsSorted);
            modelAndView.addObject("hotels", hotelsSorted);
        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}

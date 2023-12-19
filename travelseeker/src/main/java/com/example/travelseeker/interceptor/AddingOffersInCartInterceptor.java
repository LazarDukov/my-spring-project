package com.example.travelseeker.interceptor;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.model.entities.CarRent;
import com.example.travelseeker.service.AirplaneTicketsService;
import com.example.travelseeker.service.CarRentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class AddingOffersInCartInterceptor implements HandlerInterceptor {
    private final AirplaneTicketsService airplaneTicketsService;
    private final CarRentService carRentService;
    //private final HotelService hotelService;

    public AddingOffersInCartInterceptor(AirplaneTicketsService airplaneTicketsService, CarRentService carRentService) {
        this.airplaneTicketsService = airplaneTicketsService;
        this.carRentService = carRentService;

        //  this.hotelService = hotelService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals("/")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        if (request.getRequestURI().equals("/")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        //  if (request.getRequestURI().equals("/offers/hotels")) {
        //      return HandlerInterceptor.super.preHandle(request, response, handler);
        //  }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AirplaneTicket airplaneTicketsSorted = airplaneTicketsService.getSortedAirplaneTickets();
        CarRent carRents = this.carRentService.getSortedCarRents();
//        List<Hotel> hotels = this.hotelService.hotelsWithQuantityMoreThanZero();
        if (modelAndView != null) {
            modelAndView.addObject("airplaneTicket", airplaneTicketsSorted);
            modelAndView.addObject("car", carRents);
//            modelAndView.addObject("allHotels", hotels);
        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}

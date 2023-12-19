package com.example.travelseeker.interceptor;

import com.example.travelseeker.model.entities.AirplaneTicket;
import com.example.travelseeker.service.AirplaneTicketsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Configuration
public class AddingOffersInCartInterceptor implements HandlerInterceptor {
    private final AirplaneTicketsService airplaneTicketsService;
    // private final CarRentService carRentService;
    //private final HotelService hotelService;

    public AddingOffersInCartInterceptor(AirplaneTicketsService airplaneTicketsService) {
        this.airplaneTicketsService = airplaneTicketsService;
        // this.carRentService = carRentService;

        //  this.hotelService = hotelService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals("/offers/airplane-tickets/sort-by-price")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        if (request.getRequestURI().equals("/offers/cars/sort-by-price")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        if (request.getRequestURI().equals("/offers/hotels/sort-by-price")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<AirplaneTicket> airplaneTicketsSorted = airplaneTicketsService.getSortedAirplaneTickets();
//        List<CarRent> carRents = this.carRentService.carsWithQuantityMoreThanZero();
//        List<Hotel> hotels = this.hotelService.hotelsWithQuantityMoreThanZero();
        if (modelAndView != null) {
            modelAndView.addObject("allAirplaneTickets", airplaneTicketsSorted);
//            modelAndView.addObject("allCars", carRents);
//            modelAndView.addObject("allHotels", hotels);
        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}

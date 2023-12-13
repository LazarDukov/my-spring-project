package com.example.travelseeker.web;

import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.service.BuyerService;
import com.example.travelseeker.service.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class AdminController {
    private final SellerService sellerService;
    private final BuyerService buyerService;

    public AdminController(SellerService sellerService, BuyerService buyerService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
    }

    @GetMapping("/all-users")
    public String getAllUsers(ModelAndView modelAndView) {
        List<Seller> sellers = sellerService.getAllSellers();
        List<Buyer> buyers = buyerService.getAllBuyers();

        modelAndView.addObject("allSellers", sellers);
        modelAndView.addObject("allBuyers", buyers);
        return "all-users";
    }
}

package com.example.travelseeker.web;


import com.example.travelseeker.model.dtos.EditProfileDTO;
import com.example.travelseeker.model.dtos.view.UserProfileView;
import com.example.travelseeker.service.BuyerService;
import com.example.travelseeker.service.OffersService;
import com.example.travelseeker.service.SellerService;
import com.example.travelseeker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController {

    private final SellerService sellerService;
    private final BuyerService buyerService;
    private final UserService userService;


    private final OffersService offersService;

    @Autowired
    public UserController(SellerService sellerService, BuyerService buyerService,
                          UserService userService, OffersService offersService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
        this.userService = userService;

        this.offersService = offersService;
    }


    @ModelAttribute("editProfileDTO")
    public EditProfileDTO editProfileDTO() {
        return new EditProfileDTO();
    }

    @GetMapping("/my-profile")
    public String getMyProfile(Model model, Principal principal) {
        UserProfileView userProfileView = userService.getUserProfileView(principal);
        model.addAttribute("user", userProfileView);
        return "my-profile";
    }


    @GetMapping("/edit-profile")
    private String getEditProfile(Model model, Principal principal) {
        UserProfileView userProfileView = userService.getUserProfileView(principal);
        model.addAttribute("user", userProfileView);
        return "edit-profile";
    }

    // TODO: should repair showing of role not to be optional and given as a string
    @PostMapping("/edit-profile")
    public String updateUserProfile(EditProfileDTO editProfileDTO, Principal principal, BindingResult
            bindingResult, RedirectAttributes redirectAttributes) {
        userService.editProfile(editProfileDTO, principal);


        return "redirect:/users/my-profile";
//
        //       @GetMapping("/my-cart")
        //       public String getMyCart (Model model, Principal principal){
        //           User user = userService.getUserByName(principal.getName());
        //           List<AirplaneTicket> airplaneTicketsInCart = new ArrayList<>(user.getCart().getAirplaneTickets());
        //           List<CarRent> carRentsInCart = new ArrayList<>(user.getCart().getCars());
        //           List<Hotel> hotelsInCart = new ArrayList<>(user.getCart().getHotels());
        //           model.addAttribute("myAirplaneTicketCart", airplaneTicketsInCart);
        //           //TODO: should implement the next two rows in the services and point them all by thymeleaf.
        //           model.addAttribute("myCarCart", carRentsInCart);
        //           model.addAttribute("myHotelCart", hotelsInCart);
//
        //           return "my-cart";
        //       }
        //       @GetMapping("/my-orders")
        //       public String getMyOrders (Principal principal, Model model){
        //           User user = userService.getUserByName(principal.getName());
        //           List<AirplaneTicket> usersAirplaneTickets = offersService.getAirplaneTicketsOffers(principal);
//
        //           model.addAttribute("myAirplaneTicketBought", usersAirplaneTickets);
        //           //TODO: should implement the next two rows in the services and point them all by thymeleaf.
////        model.addAttribute("myCarBought", usersCarRents);
////        model.addAttribute("myHotelBought", usersHotels);
//
        //           return "my-orders";
        //       }
        //       @PostMapping("/my-cart/buy-airplane-ticket-offer/{id}")
        //       public String buyAirplaneTicketFromCart (@PathVariable UUID id, Principal principal){
        //           offersService.buyFromCart(id, principal);
        //           return "successfully-added";
        //       }
//
        //       @PostMapping("/my-cart/buy-hotel-offer/{id}")
        //       public String buyHotelFromCart (@PathVariable UUID id, Principal principal){
        //           offersService.buyFromCart(id, principal);
        //           return "successfully-added";
        //       }
//
        //       @PostMapping("/my-cart/buy-car-offer/{id}")
        //       public String buyCarFromCart (@PathVariable UUID id, Principal principal){
        //           offersService.buyFromCart(id, principal);
        //           return "successfully-added";
        //       }
//
        //   }
    }
}


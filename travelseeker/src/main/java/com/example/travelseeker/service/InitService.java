package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Cart;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.CartRepository;
import com.example.travelseeker.repository.SellerRepository;
import com.example.travelseeker.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.travelseeker.model.enums.UserRoleEnum.BUYER;
import static com.example.travelseeker.model.enums.UserRoleEnum.SELLER;


@Service
public class InitService {
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyersRepository;

    private final CartService cartService;
    private final CartRepository cartRepository;


    @Autowired
    public InitService(UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder, SellerRepository sellerRepository, BuyerRepository buyersRepository, CartService cartService, OffersService offersService, CartRepository cartRepository) {
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.sellerRepository = sellerRepository;
        this.buyersRepository = buyersRepository;

        this.cartService = cartService;
        this.cartRepository = cartRepository;
    }


    private void initRoles() {
        // UserRole admin = new UserRole().setRole(ADMIN);
        UserRole buyer = new UserRole().setRole(BUYER);
        UserRole seller = new UserRole().setRole(SELLER);

        // userRoleRepository.save(admin);
        userRoleRepository.save(buyer);
        userRoleRepository.save(seller);
    }

    @PostConstruct
    public void Init() {
        if (userRoleRepository.count() == 0) {
            initRoles();
        }
        if (buyersRepository.count() == 0) {
            initBuyerUsers();
        }
        if (sellerRepository.count() == 0) {
            initSellerUsers();
        }

    }

//    public void initAdminUsers() {
//
//
//        User userAdmin = new User().setUsername("AdminAdminov")
//                .setFirstName("Admin")
//                .setLastName("Adminov")
//                .setEmail("adminov@gmail.com")
//                .setCountry("Bulgaria")
//                .setAge(40)
//                .setPassword(passwordEncoder.encode("lazar123"))
//                .setRoles(userRoleRepository.findUserRoleByRole(ADMIN));
//
//
//        userRepository.saveAndFlush(userAdmin);
//    }
    private void initBuyerUsers() {


        Buyer userBuyer = new Buyer();
        userBuyer.setUsername("BuyerBuyerov")
                .setFirstName("Buyer")
                .setLastName("Buyerov")
                .setEmail("Buyerov@gmail.com")
                .setCountry("Afganistan")
                .setAge(30)
                .setPassword(passwordEncoder.encode("lazar123"))
                .setRoles(userRoleRepository.findUserRoleByRole(BUYER));
        userBuyer.setCart(cartService.getNewCart());

        Cart cart = userBuyer.getCart();
        cart.setBuyer(userBuyer);
        buyersRepository.saveAndFlush(userBuyer);
        cartRepository.save(cart);
    }


    private void initSellerUsers() {


        Seller userSeller = new Seller();
        userSeller.setUsername("SellerSellerov")
                .setFirstName("Seller")
                .setLastName("Sellerov")
                .setEmail("sellerov@gmail.com")
                .setCountry("Afganistan")
                .setAge(60)
                .setPassword(passwordEncoder.encode("lazar123"))
                .setRoles(userRoleRepository.findUserRoleByRole(SELLER));


        sellerRepository.saveAndFlush(userSeller);
    }


}


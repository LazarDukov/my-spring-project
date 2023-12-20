package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.Admin;
import com.example.travelseeker.model.entities.Buyer;
import com.example.travelseeker.model.entities.Seller;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.model.enums.UserRoleEnum;
import com.example.travelseeker.repository.AdminRepository;
import com.example.travelseeker.repository.BuyerRepository;
import com.example.travelseeker.repository.SellerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationUserDetailsTest {
    private ApplicationUserDetailsService toTest;

    private static final String USERNAME = "testUser";

    @Mock
    private SellerRepository mockSellerRepository;
    @Mock
    private BuyerRepository mockBuyerRepository;
    @Mock
    private AdminRepository mockAdminRepository;


    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailsService(mockAdminRepository, mockSellerRepository, mockBuyerRepository);
    }

    @Test
    void testLoadUserByUsernameAsAdmin() {

        //start Arrange
        UserRole testAdminRole = new UserRole().setRole(UserRoleEnum.ADMIN);

        String EXISTING_USERNAME = "AdminAdminov";
        Admin testAdmin = new Admin();
        testAdmin.setUsername(EXISTING_USERNAME).
                setPassword("lazar123").
                setRoles(List.of(testAdminRole));

        when(mockAdminRepository.findAdminByUsername(EXISTING_USERNAME)).
                thenReturn(Optional.of(testAdmin));
        //end Arrange

        UserDetails adminDetails = toTest.loadUserByUsername(EXISTING_USERNAME);

        //Assert
        Assertions.assertNotNull(adminDetails);
        Assertions.assertEquals(EXISTING_USERNAME, adminDetails.getUsername());
        Assertions.assertEquals(EXISTING_USERNAME, adminDetails.getUsername());
        Assertions.assertEquals(testAdmin.getPassword(), adminDetails.getPassword());

        Assertions.assertEquals(1,
                adminDetails.getAuthorities().size(),
                "The authorities are supposed to be just one - ADMIN.");
        assertRoleAdmin(adminDetails.getAuthorities());

    }

    private void assertRoleAdmin(Collection<? extends GrantedAuthority> authorities) {
        authorities.
                stream().
                filter(a -> "ROLE_ADMIN".equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + "ROLE_ADMIN" + " not found!"));
    }

    @Test
    void testLoadUserByUsernameAsSeller() {

        //start Arrange
        UserRole testSellerRole = new UserRole().setRole(UserRoleEnum.SELLER);

        String EXISTING_USERNAME = "SellerSellerov";
        Seller testSeller = new Seller();
        testSeller.setUsername(EXISTING_USERNAME).
                setPassword("lazar123").
                setRoles(List.of(testSellerRole));

        when(mockSellerRepository.findSellerByUsername(EXISTING_USERNAME)).
                thenReturn(Optional.of(testSeller));
        //end Arrange

        UserDetails sellerDetails = toTest.loadUserByUsername(EXISTING_USERNAME);

        //Assert
        Assertions.assertNotNull(sellerDetails);
        Assertions.assertEquals(EXISTING_USERNAME, sellerDetails.getUsername());
        Assertions.assertEquals(EXISTING_USERNAME, sellerDetails.getUsername());
        Assertions.assertEquals(sellerDetails.getPassword(), sellerDetails.getPassword());

        Assertions.assertEquals(1,
                sellerDetails.getAuthorities().size(),
                "The authorities are supposed to be just one - SELLER.");
        assertRoleSeller(sellerDetails.getAuthorities());

    }

    private void assertRoleSeller(Collection<? extends GrantedAuthority> authorities) {
        authorities.
                stream().
                filter(a -> "ROLE_SELLER".equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + "ROLE_SELLER" + " not found!"));
    }

    @Test
    void testLoadUserByUsernameAsBuyer() {

        //start Arrange
        UserRole testBuyerRole = new UserRole().setRole(UserRoleEnum.BUYER);

        String EXISTING_USERNAME = "BuyerBuyerov";
        Buyer testBuyer = new Buyer();
        testBuyer.setUsername(EXISTING_USERNAME).
                setPassword("lazar123").
                setRoles(List.of(testBuyerRole));

        when(mockBuyerRepository.findBuyerByUsername(EXISTING_USERNAME)).
                thenReturn(Optional.of(testBuyer));
        //end Arrange

        UserDetails buyerDetails = toTest.loadUserByUsername(EXISTING_USERNAME);

        //Assert
        Assertions.assertNotNull(buyerDetails);
        Assertions.assertEquals(EXISTING_USERNAME, buyerDetails.getUsername());
        Assertions.assertEquals(EXISTING_USERNAME, buyerDetails.getUsername());
        Assertions.assertEquals(testBuyer.getPassword(), buyerDetails.getPassword());

        Assertions.assertEquals(1,
                buyerDetails.getAuthorities().size(),
                "The authorities are supposed to be just one - BUYER.");
        assertRoleBuyer(buyerDetails.getAuthorities());

    }
    private void assertRoleBuyer(Collection<? extends GrantedAuthority> authorities) {
        authorities.
                stream().
                filter(a -> "ROLE_BUYER".equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + "ROLE_BUYER" + " not found!"));
    }







    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("Not existing!")
        );
    }


}
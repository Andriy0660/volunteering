package com.azn.tracking_volunteer_hours.controller;

import com.azn.tracking_volunteer_hours.email.BuildEmailMessage;
import com.azn.tracking_volunteer_hours.email.ticket.EmailTicketSenderService;
import com.azn.tracking_volunteer_hours.entity.Company;
import com.azn.tracking_volunteer_hours.entity.Offer;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.entity.UserDetailsImpl;
import com.azn.tracking_volunteer_hours.exception.BadRequestException;
import com.azn.tracking_volunteer_hours.service.CompaniesService;
import com.azn.tracking_volunteer_hours.service.OffersService;
import com.azn.tracking_volunteer_hours.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompaniesController {
    private final CompaniesService companiesService;
    private final OffersService offerService;
    private final UserService userService;
    private final EmailTicketSenderService ticketService;
    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies() {

        return ResponseEntity.ok(companiesService.findAll().stream()
                .sorted((i1, i2) -> i2.getNum().compareTo(i1.getNum())).toList());
    }
    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getOffers(@RequestParam("id")Long id){
        Company company = companiesService.findById(id).orElseThrow();
        return ResponseEntity.ok(company.getOffers());
    }
    @PutMapping("/pay")
    public ResponseEntity<?> pay(@RequestParam("id")Long id){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        Offer offer = offerService.findById(id).orElseThrow();
        if(user.getScores()<offer.getPrice()){
            throw new BadRequestException("Sorry, you need work more");
        }
        user.setScores(user.getScores()-offer.getPrice());
        userService.save(user);
        ticketService.send(user.getEmail(), BuildEmailMessage.buildEmailTicket(user.getLastname() + user.getFirstname(),offer.getName()));
        return ResponseEntity.ok().build();
    }
}

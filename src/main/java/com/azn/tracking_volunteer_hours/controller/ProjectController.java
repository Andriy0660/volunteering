package com.azn.tracking_volunteer_hours.controller;

import com.azn.tracking_volunteer_hours.entity.Project;
import com.azn.tracking_volunteer_hours.entity.User;
import com.azn.tracking_volunteer_hours.entity.UserDetailsImpl;
import com.azn.tracking_volunteer_hours.exception.BadRequestException;
import com.azn.tracking_volunteer_hours.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping()
    public ResponseEntity<List<Project>> getProjects(@RequestParam boolean isFuture) {

        if(isFuture) {
            return ResponseEntity.ok(projectService.findAllByStartTimeIsAfter(LocalDateTime.now()));
        }
        return ResponseEntity.ok(projectService.findAllByStartTimeIsBefore(LocalDateTime.now()))
    }

    @GetMapping()
    public ResponseEntity<?> getAvailableCars() {


return huy;
    }

    @GetMapping("/owned")
    public ResponseEntity<List<Car>> getOwnedCars() {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        List<Car> ownedCars = user.getOwnedCars();
        return ResponseEntity.ok(ownedCars);
    }

    @GetMapping("/rented")
    public ResponseEntity<List<Car>> getRentedCars() {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        List<Long> integers = bookingService.findAllByRenterId(user.getId())
                .stream().map(i->i.getCarId()).collect(Collectors.toList());

        return ResponseEntity.ok(carService.findAllByIdIsIn(integers));
    }

    @PutMapping("/rent")
    public ResponseEntity<?> rentCar(@RequestParam("id") Long id,
                                     @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime,
                                     @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endTime) {

        if (startTime.isAfter(endTime)) {
            throw new BadRequestException("Start time must be before end time");
        }
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Start time must be after now");
        }

        Car car = carService.findById(id);

        if (!carService.isCarAvailable(car, startTime, endTime)) {
            throw new ConflictException("This auto is not available for this period");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User renter = userDetails.getUser();
        if(renter.isVolunteer()==false){
            throw new BadRequestException("You are not a volunteer, you can not rent car");
        }
        User owner = car.getOwner();

        if (renter.getId().equals(owner.getId())) {
            throw new BadRequestException("You can not rent your car");
        }

        Booking booking = new Booking(car.getId(), renter.getId(), startTime, endTime);

        bookingService.save(booking);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCar(@RequestBody @Valid AddCarRequest carInfo) throws IOException {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        Car car = CarMapper.mapToAddCar(carInfo);
        car.setOwner(user);

        car = carService.save(car);

        return ResponseEntity.ok(new CarIdResponse(car.getId()));
    }
}

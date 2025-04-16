package hotel.booking.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hotel.booking.model.Booking;
import hotel.booking.repository.BookingRepository;

import java.util.List;

@Controller
public class HomeController {

    private final BookingRepository bookingRepository;

    public HomeController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/")
    public String home() {
      return "redirect:/bookings/all";
    }
    
    @GetMapping("/login")
    public String viewLogin() {
    
    return "login";
    }

  }

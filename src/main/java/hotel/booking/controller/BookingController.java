package hotel.booking.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import hotel.booking.BookingApplication;
import hotel.booking.model.Booking;
import hotel.booking.model.BookingDetails;
import hotel.booking.model.Guest;
import hotel.booking.model.Room;
import hotel.booking.repository.BookingRepository;
import hotel.booking.repository.GuestRepository;
import hotel.booking.repository.RoomRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/bookings")

public class BookingController {

    private final BookingApplication bookingApplication;
    
    @Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private GuestRepository guestRepository;


    BookingController(BookingApplication bookingApplication) {
        this.bookingApplication = bookingApplication;
    }

	
	
@GetMapping("/all")
	public String listBookings(@RequestParam(name="sdate", required=false, defaultValue="") String sdate,  Model model) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String strDate = LocalDateTime.now().minusDays(60).format(formatter);

		List<Map<String,BookingDetails>>bookings = bookingRepository.findAllWithGuestRoom(sdate);
		model.addAttribute("bookings", bookings);
		model.addAttribute("sdate", sdate.isEmpty() ? strDate : sdate);
		return "index";	
	}

	@GetMapping("/json")
@ResponseBody
public List<Map<String,BookingDetails>> json(@RequestParam(name="sdate", required=false, defaultValue="") String sdate){
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String strDate = LocalDateTime.now().minusDays(60).format(formatter);

		return bookingRepository.findAllWithGuestRoom(sdate.isEmpty() ? strDate : sdate);
}
	
	
	@GetMapping("/new")
	public String showNewBookingForm(Model model) {
		model.addAttribute("booking", new Booking());
		return "newBooking";
	}
	@PostMapping("/ins")
public String saveBooking(@ModelAttribute("booking") Booking booking, RedirectAttributes rAttributes) {
    String msg;
    try {
        
        Booking savedBooking = bookingRepository.save(booking);

        
        Guest guest = guestRepository.findById(savedBooking.getGuestsid())
            .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id: " + savedBooking.getGuestsid()));

       
        msg = "Prenotazione numero #" + savedBooking.getId() + " per " + guest.getLastName() + " " + guest.getFirstName() + " inserita con successo!";
        rAttributes.addFlashAttribute("msg", msg);
        rAttributes.addFlashAttribute("style", "alert alert-success");
    } catch (Exception e) {
        msg = "Non hai inserito correttamente la prenotazione!";
        rAttributes.addFlashAttribute("msg", msg);
        rAttributes.addFlashAttribute("style", "alert alert-danger");
    }

    return "redirect:/bookings/all";
}


	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") int id, Model model) {
		if(bookingRepository.existsById(id)) {
			Booking booking = bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid booking Id:" + id));
			int guestId = booking.getGuestsid();
			Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + id));
			int roomId = booking.getRoomsid();
			Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
			model.addAttribute("booking", booking);
			model.addAttribute( "guestName", guest.getLastName() +" "+  guest.getFirstName());
			model.addAttribute("roomNumber", room.getRoomNumber() +" - "+ room.getType());
			return "editBooking";
		}
		else return "redirect:/";
	}
	
	@PostMapping("/upd")
public String updateBooking(@ModelAttribute("booking") Booking booking, RedirectAttributes rAttributes) {
	String msg;
	try {
        
        Guest guest = guestRepository.findById(booking.getGuestsid())
            .orElseThrow(() -> new IllegalArgumentException("Invalid guest Id: " + booking.getGuestsid()));
	bookingRepository.save(booking);
	msg = "La modifica alla prenotazione #" + booking.getId() + " per " + guest.getLastName() + " è avvenuta con successo";
	rAttributes.addFlashAttribute("msg", msg);
	rAttributes.addFlashAttribute("style", "alert alert-success");
	} catch (Exception e) {
	msg = "errore di modifica";
	rAttributes.addFlashAttribute("msg", msg);
	rAttributes.addFlashAttribute("style", "alert alert-danger");
	}
	
	return "redirect:/bookings/all";
	}

	
	@GetMapping("/delete/{id}")
public String delete(@PathVariable("id") int id, RedirectAttributes rAttributes) {
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
String msg = "DELETE OK";
try {
Booking booking = bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid booking Id:" + id));
int guestId = booking.getGuestsid();
Guest guest = guestRepository.findById(guestId).orElseThrow(() -> new IllegalArgumentException("Invalid guest Id:" + id));

msg = "Prenotazione #" + booking.getId() + " Eliminata! ["+guest.getLastName()+" "+guest.getFirstName() +" | "+ booking.getCheckin().format(formatter) +" - "+ booking.getCheckout().format(formatter) +"]";
if(bookingRepository.existsById(id)) {bookingRepository.deleteById(id);}
rAttributes.addFlashAttribute("msg", msg);
rAttributes.addFlashAttribute("style", "alert alert-success");
} catch (Exception e) {
msg = "DELETE ERROR";
rAttributes.addFlashAttribute("msg", msg);
rAttributes.addFlashAttribute("style", "alert alert-danger");
}

return "redirect:/bookings/all";
}



}

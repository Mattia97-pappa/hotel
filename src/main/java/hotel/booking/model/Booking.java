package hotel.booking.model;



import jakarta.persistence.*;

import java.math.BigDecimal;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Bookings")
public class Booking {
    
    @Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;

    @Column(name = "check_in")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkin;

    @Column(name = "check_out")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkout;

    @Column(name = "total_amount")
	private BigDecimal totalamount;

    @Column(name = "guests_id")
	private int guestsid;

    @Column(name = "rooms_id")
	private int roomsid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public int getGuestsid() {
        return guestsid;
    }

    public void setGuestsid(int guestsid) {
        this.guestsid = guestsid;
    }

    public int getRoomsid() {
        return roomsid;
    }

    public void setRoomsid(int roomsid) {
        this.roomsid = roomsid;
    }







}

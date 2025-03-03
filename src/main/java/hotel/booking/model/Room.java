package hotel.booking.model;
import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "Rooms")
public class Room {

    @Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
    
    @Column(name = "roomNumber")
    private String roomNumber;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "price")
    private BigDecimal price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String number) {
        this.roomNumber = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}

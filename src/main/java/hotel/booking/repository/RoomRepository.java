package hotel.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import hotel.booking.model.Room;
public interface RoomRepository extends JpaRepository<Room, Integer>{

}
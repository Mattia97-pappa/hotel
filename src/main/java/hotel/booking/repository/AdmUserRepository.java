package hotel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import hotel.booking.model.AdmUser;

public interface AdmUserRepository extends JpaRepository<AdmUser, Integer> {
@NativeQuery("SELECT * FROM `AdmUser` WHERE username= ?1 LIMIT 1")
AdmUser findByUsername(String username); 
}

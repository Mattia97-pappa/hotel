package hotel.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import hotel.booking.repository.AdmUserRepository;
import hotel.booking.model.AdmUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

@Autowired
private AdmUserRepository userRepository;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
AdmUser user = userRepository.findByUsername(username);
if (user == null) {
throw new UsernameNotFoundException("User not found");
}


return
User.withDefaultPasswordEncoder()
.username(user.getUsername())
.password(user.getPassword())
.roles(user.getRoles())
.build();
}
}
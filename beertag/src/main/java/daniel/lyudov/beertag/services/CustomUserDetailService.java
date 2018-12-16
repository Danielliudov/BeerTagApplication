package daniel.lyudov.beertag.services;

import daniel.lyudov.beertag.exceptions.UserAlreadyExistAuthenticationException;
import daniel.lyudov.beertag.models.CustomUserDetails;
import daniel.lyudov.beertag.models.Role;
import daniel.lyudov.beertag.models.User;
import daniel.lyudov.beertag.repositories.RoleRepository;
import daniel.lyudov.beertag.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private static final int ROLE_USER = 2;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("Incorrect email or password"));
        return optionalUser
                .map(CustomUserDetails::new).get();
    }

    public User getUserByUserId(int userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            return optional.get();
        } else throw new UsernameNotFoundException("User not found");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) throws UserAlreadyExistAuthenticationException {
        // If user exists, throw exception
        if (alreadyExists(user.getUsername()))
            throw new UserAlreadyExistAuthenticationException("User already exists");

        Optional<Role> optionalRole = roleRepository.findById(ROLE_USER);
        optionalRole.ifPresent(role -> user.setRoles(new HashSet<>(Collections.singletonList(role))));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private boolean alreadyExists(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.isPresent();
    }
}

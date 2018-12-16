package daniel.lyudov.beertag.controllers;

import daniel.lyudov.beertag.exceptions.UserAlreadyExistAuthenticationException;
import daniel.lyudov.beertag.models.User;
import daniel.lyudov.beertag.services.CustomUserDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final CustomUserDetailService service;

    public UserController(CustomUserDetailService service) {
        this.service = service;
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/secured/all")
    public List getUsers() {
        return service.getAllUsers();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return service.getUserByUserId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/me")
    public @ResponseBody
    User getCurrentUser(Principal principal) throws UsernameNotFoundException {
        return (User) service.loadUserByUsername(principal.getName());
    }

    @ResponseBody
    @PostMapping("/signup")
    public User registerUser(@RequestBody User user) throws UserAlreadyExistAuthenticationException {
        return service.registerUser(user);
    }

}
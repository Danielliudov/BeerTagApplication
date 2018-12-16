package daniel.lyudov.beertag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Username already exists")
public class UserAlreadyExistAuthenticationException extends RuntimeException {

    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }

}
package ru.restaurants.web;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.restaurants.model.User;
import ru.restaurants.repository.UserRepository;
import ru.restaurants.to.ToUser;


@Component
public class UniqueMailAndNameValidator implements Validator {

    private final UserRepository userRepository;

    public UniqueMailAndNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) { //Или User?
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ToUser toUser = (ToUser)o;
        if (StringUtils.hasText(toUser.getEmail())){
            User userBD = userRepository.getByEmail(toUser.getEmail().toLowerCase());
            if (userBD != null && toUser.getEmail().equals(userBD.getEmail())){
                errors.rejectValue("email", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
            }
       }
    }
}


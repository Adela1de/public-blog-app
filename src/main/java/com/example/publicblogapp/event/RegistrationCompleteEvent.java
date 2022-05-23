package com.example.publicblogapp.event;

import com.example.publicblogapp.model.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;

    public RegistrationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }
}

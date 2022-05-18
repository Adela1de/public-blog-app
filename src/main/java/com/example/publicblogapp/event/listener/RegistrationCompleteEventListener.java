package com.example.publicblogapp.event.listener;

import com.example.publicblogapp.event.RegistrationCompleteEvent;
import com.example.publicblogapp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent>
{

    private final UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event)
    {
        var user = event.getUser();
        var token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user, token);

        String url = event.getApplicationUrl()+"/users/verifyRegistration?token="+token;
        log.info("Click the link to verify your account: {}", url);
    }
}

package com.codingoutreach.clubservice.schedule;

import com.codingoutreach.clubservice.controllers.DO.ResetPasswordCreationRequest;
import com.codingoutreach.clubservice.repository.ClubRepository;
import com.codingoutreach.clubservice.repository.DTO.Club;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.time.Instant;
import java.util.List;

@EnableScheduling
@Component
public class Scheduler {

    private ClubRepository clubRepository;

    public Scheduler(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }


    @Scheduled(cron = "0 0 * * 0")
    public void weeklyRandomizer() {
        clubRepository.clearFeaturedClubs();
        List<Club> allClubs = clubRepository.getAllClubs();
        Collections.shuffle(allClubs);
        for (int i = 0; i < Math.min(allClubs.size(), 3); i++) {
            clubRepository.pushFeaturedClub(allClubs.get(i));
            clubRepository.getFeaturedClubs();
        }
    }
    @Scheduled(cron="0 0 8 * * *")
    public void nightlySchedule() {
        //Go through all reset password requests and remove where the expiration date is at least 3 hours before the current time

        List<ResetPasswordCreationRequest> requests = clubRepository.getAllResetPasswordRequests();
        for (ResetPasswordCreationRequest request : requests) {
            if (request.getExpirationDate().isBefore(Instant.now().minusSeconds(3 * 60 * 60))) {
                clubRepository.deleteResetPasswordCode(request.getRequestId());
            }
        }
    }
}

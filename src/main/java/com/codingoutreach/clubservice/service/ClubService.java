package com.codingoutreach.clubservice.service;

import com.codingoutreach.clubservice.dos.ClubInformation;
import com.codingoutreach.clubservice.dos.ClubSocialDO;
import com.codingoutreach.clubservice.repository.ClubRepository;
import com.codingoutreach.clubservice.repository.DTO.Club;
import com.codingoutreach.clubservice.repository.DTO.ClubSocial;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClubService {
    private ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public ClubInformation getClubInformationByClubId(UUID clubId) {
        Club club = clubRepository.getClubByClubId(clubId);
        List<ClubSocial> clubSocials = clubRepository.getClubSocialsByClubId(clubId);
        List<String> categories = clubRepository.getClubCategoriesByClubId(clubId);

        List<ClubSocialDO> clubSocialDOs = clubSocials.stream().map(clubSocial -> new ClubSocialDO(clubSocial.getSocialName(), clubSocial.getSocialLink())).collect(Collectors.toList());

        return new ClubInformation(
                club.getClubID(),
                club.getName(),
                club.getDescription(),
                club.getProfilePictureUrl(),
                clubSocialDOs,
                categories
        );
    }

}

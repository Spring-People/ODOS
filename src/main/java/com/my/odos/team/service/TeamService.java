package com.my.odos.team.service;

import com.my.odos.domain.Member;
import com.my.odos.domain.Team;
import com.my.odos.team.respository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public Integer updateUploadTime(int id, int time, int am_pm) {
        Team team = teamRepository.findById(id);
        if (am_pm == 1) time += 12;
        if (time == 24) time = 0;

        team.setUploadTime(LocalTime.of(time, 0, 0));

        return id;
    }

    public Team makeTeam(Member sender, Member receiver) {
        Team team = new Team();
        team.setUploadTime(LocalTime.of(1,0,0));
        teamRepository.save(team);

        sender.setGroupId(team.getId());
        receiver.setGroupId(team.getId());

        return team;
    }
}

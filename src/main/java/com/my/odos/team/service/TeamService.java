package com.my.odos.team.service;

import com.my.odos.domain.Member;
import com.my.odos.domain.Team;
import com.my.odos.problem.service.ProblemService;
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
    private final ProblemService problemService;

    /*
     * updateUploadTime: am-pm에 따라서 시간을 변경해 update한다.
     * */
    @Transactional
    public Integer updateUploadTime(int id, int time, int am_pm) {
        Team team = teamRepository.findById(id);
        if (am_pm == 1) time += 12;
        if (time == 12) time = 0;
        if (time == 24) time = 12;


        team.setUploadTime(LocalTime.of(time, 0, 0));

        return id;
    }

    /*
     * makeTeam: Team 레코드를 만들고 sender와 receiver의 team 정보를 업데이트한다.
     * */
    public Team makeTeam(Member sender, Member receiver) {
        Team team = new Team();

        team.setUploadTime(LocalTime.of(12,0,0));

        team.setSolveLimit(0);
        Team myTeam = teamRepository.save(team);

        sender.setGroupId(team.getId());
        receiver.setGroupId(team.getId());

        problemService.makeProblem(myTeam.getId()); // 만들어진 팀에게 첫 문제를 만든다.

        return team;
    }
}

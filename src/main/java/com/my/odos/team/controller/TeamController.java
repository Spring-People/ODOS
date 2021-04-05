package com.my.odos.team.controller;

import com.my.odos.domain.Team;
import com.my.odos.team.respository.TeamRepository;
import com.my.odos.team.service.TeamService;
import com.my.odos.team.vo.TeamUploadTimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final TeamRepository teamRepository;

    @PutMapping("/api/updateTeamUploadTime/{id}")
    @ResponseBody
    public Integer updateUploadTime(@PathVariable Integer id, @RequestBody TeamUploadTimeRequest request) {
        int time = request.getTime();
        int am_pm = request.getAm_pm();

        System.out.println("" + time + am_pm);

        return teamService.updateUploadTime(id, time, am_pm);
    }


}
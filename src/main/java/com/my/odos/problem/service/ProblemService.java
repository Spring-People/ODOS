package com.my.odos.problem.service;

import com.my.odos.domain.Member;
import com.my.odos.domain.Problem;
import com.my.odos.domain.Team;
import com.my.odos.exception.ProblemException;
import com.my.odos.login.vo.AuthInfo;
import com.my.odos.team.respository.TeamRepository;
import com.my.odos.problem.repository.UserRepository;
import com.my.odos.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Scheduled(cron="0 0 0/1 * * *")
    public void upload() {

        //현재 시간 구하기
        LocalTime nowTime = LocalTime.now();
        LocalTime checkTime = LocalTime.of(nowTime.getHour(),nowTime.getMinute(),0);

        List<Team> allTeam = teamRepository.findAll();

        for(Team t : allTeam){

            if(t.getUploadTime() != checkTime)
                continue;

            t.setSolveLimit(0);

            makeProblem(t.getId());
        }
    }
    
    public void makeProblem(int groupId) {
        //1000~21000 까지 랜덤 번호 생성
        Random rd = new Random();
        int number = rd.nextInt(20000) + 1000;

        Problem problem = new Problem();
        problem.setGroupId(groupId);
        problem.setNum(number);
        problem.setSolved(0);

        problemRepository.save(problem);
    }

    public Problem findProblem(HttpSession session, int id){

        Problem problem = problemRepository.findById(id);

        if(problem == null)
            throw new ProblemException("해당 problem이 없습니다");

        AuthInfo currentUser = (AuthInfo) session.getAttribute("authInfo");
        Member member = userRepository.findById(currentUser.getId());

        if(problem.getGroupId() != member.getGroupId())
            throw new ProblemException("해당 problem에 권한이 없습니다");

        return problem;
    }

    @Transactional
    public int updateSolved(int id, int check_number){

        Problem problem = problemRepository.findById(id);
        Team team = teamRepository.findById(problem.getGroupId());

        problem.setSolved(check_number);

        //둘다 문제를 풀었을때
        if(check_number == -1)
        {
            int this_solved_limit = team.getSolveLimit();
            team.setSolveLimit(this_solved_limit+1);
        }

        return check_number;
    }
}

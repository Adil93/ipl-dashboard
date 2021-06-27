package com.myworks.ipldashboard.data;

import java.util.HashMap;
import java.util.Map;

import javax.batch.runtime.BatchStatus;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.myworks.ipldashboard.model.Team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (BatchStatus.COMPLETED.name().equals(jobExecution.getStatus().name())) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamMap = new HashMap<>();

            em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class).getResultList()
                    .stream().map(result -> new Team((String) result[0], (long) result[1]))
                    .forEach(team -> teamMap.put(team.getName(), team));

            em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class).getResultList()
                    .stream().map(result -> new Team((String) result[0], (long) result[1])).forEach(team -> {
                        if (teamMap.containsKey(team.getName())) {
                            Team exist = teamMap.get(team.getName());
                            exist.setTotalMatches(exist.getTotalMatches() + team.getTotalMatches());
                        } else {
                            teamMap.put(team.getName(), team);
                        }
                    });

            em.createQuery("select m.winner, count(*) from Match m group by m.winner", Object[].class).getResultList()
                    .stream().forEach(result -> {
                        if (!ObjectUtils.isEmpty(teamMap.get((String) result[0])))
                            teamMap.get((String) result[0]).setTotalWins((long) result[1]);
                    });

            teamMap.values().forEach(team -> {
                log.info(team.toString());
                em.persist(team);
            });

            teamMap.values().forEach(team -> {
                System.out.println(team.toString());
            });
        }
    }
}
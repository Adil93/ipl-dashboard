package com.myworks.ipldashboard.data;

import com.myworks.ipldashboard.model.Match;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        log.info("Begin transforming");
        Match match = new Match();

        match.setId(Long.parseLong(matchInput.getId()));

        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setCity(matchInput.getCity());
        match.setVenue(matchInput.getVenue());

        String firstInningsTeam, secondInningsteam;
        String tossLoser = getTossLoser(matchInput.getToss_winner(), matchInput.getTeam1(), matchInput.getTeam2());

        if ("bat".equals(matchInput.getToss_decision())) {
            firstInningsTeam = matchInput.getToss_winner();
            secondInningsteam = tossLoser;
        } else {
            secondInningsteam = matchInput.getToss_winner();
            firstInningsTeam = tossLoser;
        }

        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsteam);
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        return match;
    }

    private String getTossLoser(String tossWinner, String team1, String team2) {
        return tossWinner.equals(team1) ? team2 : team1;
    }

}
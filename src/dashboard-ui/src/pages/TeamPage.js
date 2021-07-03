import React, { useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';



export const TeamPage = () => {

  const emptyData = {
    "id": 0,
    "name": "",
    "totalMatches": 0,
    "totalWins": 0,
    "matches": [
      {
        "id": "",
        "city": "",
        "date": "",
        "playerOfMatch": "",
        "venue": "",
        "team1": "",
        "team2": "",
        "tossWinner": "",
        "tossDecision": "",
        "winner": "",
        "result": "",
        "resultMargin": "",
        "eliminator": null,
        "umpire1": "",
        "umpire2": ""
      }
    ]
  }
  const [team, setTeam] = useState(emptyData);

  useEffect(() => {

    const fetchMatches = async () => {
      const response = await fetch('http://localhost:8085/team/Rajasthan Royals');
      const data = await response.json();
      setTeam(data);
    };

    fetchMatches();
  }, [])


  return (
    <div className="TeamPage">
      <h1>{team.name}</h1>
      <MatchDetailCard match={team.matches[0]} />

      {team.matches.slice(1).map(match => <MatchSmallCard match={match} />)}

    </div>
  );
}

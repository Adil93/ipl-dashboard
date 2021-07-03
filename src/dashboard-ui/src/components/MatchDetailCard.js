import React from 'react';
import { Link } from 'react-router-dom';


export const MatchDetailCard = ({ name, match }) => {
  if (!match) return null;
  const opponent = match.team1 === name ? match.team2 : match.team1;
  const opponentRoute = `/teams/${opponent}`;
  return (
    <div className="MatchDetailCard">
      <h3>Latest Match</h3>
      <h4>Match Details</h4>
      <h4>vs
        <Link to={opponentRoute}> {opponent}</Link>
      </h4>
      <h2>{match.date}</h2>
      <h3>{match.venue}</h3>
      <h3>{match.winner} won by {match.resultMargin} {match.result}</h3>

    </div>
  );
}

import React from 'react';
import { Link } from 'react-router-dom';


export const MatchSmallCard = ({ name, match }) => {
  const opponent = match.team1 === name ? match.team2 : match.team1;
  const opponentRoute = `/teams/${opponent}`;
  return (
    <div className="MatchSmallCard">
      <h3>vs
        <Link to={opponentRoute}> {opponent}</Link>
      </h3>
      <p>
        {match.winner} won by {match.resultMargin} {match.result}
      </p>
    </div>
  );
}

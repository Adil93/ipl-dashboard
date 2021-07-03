import { MatchDetailCard } from 'components/MatchDetailCard';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

export const MatchPage = () => {

    const emptyReponse = [
        {
            "id": 0,
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
        }];

    const { name, year } = useParams();
    const [matches, setMatches] = useState(emptyReponse);
    useEffect(() => {
        const fetchMatches = async () => {
            const response = await fetch(`http://localhost:8085/team/${name}/matches?year=${year}`);
            const data = await response.json();
            setMatches(data);
        };

        fetchMatches();
    }, [name, year])

    return (
        <div className="MatchPage">
            <h1>Match Page</h1>
            {
                matches.map(match => <MatchDetailCard name={name} match={match} />)
            }
        </div>
    );
}

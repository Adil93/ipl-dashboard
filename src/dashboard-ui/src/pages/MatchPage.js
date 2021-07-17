import { MatchDetailCard } from 'components/MatchDetailCard';
import { YearSelector } from 'components/YearSelector';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './scss/MatchPage.scss';

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
            <div className="year-selector">
                <h3>Select year</h3>
                <YearSelector name={name} />
            </div>
            <div>
                <h1 className='page-heading'>{name} matches in {year}</h1>
                {
                    matches.map(match => <MatchDetailCard name={name} match={match} />)
                }
            </div>
        </div>
    );
}

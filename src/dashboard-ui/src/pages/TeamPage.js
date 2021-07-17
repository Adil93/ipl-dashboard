import React, { useEffect, useState } from 'react';
import { PieChart } from 'react-minimal-pie-chart';
import { Link, useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import './scss/TeamPage.scss';


export const TeamPage = () => {

    const emptyData = {
        "id": 0,
        "name": "",
        "totalMatches": 0,
        "totalWins": 0,
        "matches": [{
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
        }]
    }
    const [team, setTeam] = useState(emptyData);
    const { name } = useParams();

    useEffect(() => {

        const fetchTeam = async () => {
            const response = await fetch(`http://localhost:8085/team/${name}`);
            const data = await response.json();
            setTeam(data);
        };

        fetchTeam();
    }, [name])

    if (!team || !team.name) {
        return <h1 > Team not found </h1>
    }
    return (<div className="TeamPage" >
        <div className="team-name-section"><h1 className="team-name"> {team.name} </h1></div>
        <div className="win-loss-section">
            Wins / Losses
            <PieChart
                data={[
                    { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a34d5d' },
                    { title: 'Wins', value: team.totalWins, color: '#4da375' }
                ]}
            />
        </div>
        <div className="match-detail-section">
            <h3>Latest Matches</h3>
            <MatchDetailCard name={team.name} match={team.matches[0]} /></div>

        {
            team.matches.slice(1).map(match => < MatchSmallCard name={team.name}
                match={match} />)
        }
        <div className="more-link">
            <Link to={`/teams/${name}/matches/${process.env.REACT_APP_END_YEAR}`}> More {'>>>'}</Link>
        </div>

    </div>
    );
}
import { TeamTile } from 'components/TeamTile';
import React, { useEffect, useState } from 'react';
import Logo from '../ipl-logo.jpg';
import './scss/HomePage.scss';
export const HomePage = () => {
    const emptyData = [{
        "id": 0,
        "name": "",
        "totalMatches": 0,
        "totalWins": 0,
        "matches": null
    }]

    const [teams, setTeams] = useState(emptyData);

    useEffect(() => {

        const fetchAlTeams = async () => {
            const response = await fetch(`http://localhost:8085/team`);
            const data = await response.json();
            setTeams(data);
        };

        fetchAlTeams();
    }, [])


    return (<div className="HomePage" >
        <div className="header-section">
            <img className="image" src={Logo} alt="Logo" ></img>
            <h1 className="app-name"> IPL Dashboard </h1>
        </div>
        <div className='team-grid'>
            {
                teams.map(team =>
                    <TeamTile teamName={team.name} />
                )
            }

        </div>
    </div >
    );
}
import React from 'react';
import { Link } from 'react-router-dom';
import './scss/YearSelector.scss';

export const YearSelector = ({ name }) => {
    let years = [];
    const startYear = +process.env.REACT_APP_START_YEAR;
    const endYear = +process.env.REACT_APP_END_YEAR;

    for (let i = startYear; i <= endYear; i++) {
        years.push(i);
    }
    return (
        <ol className='YearSelector'>
            {
                years.map(year => (

                    <li>
                        <Link to={`/teams/${name}/matches/${year}`}> {year}</Link>
                    </li>
                ))
            }
        </ol>

    );
}
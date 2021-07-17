import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.scss';
import { HomePage } from './pages/HomePage';
import { MatchPage } from './pages/MatchPage';
import { TeamPage } from './pages/TeamPage';

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path="/teams/:name/matches/:year">
            <MatchPage />
          </Route>
          <Route path="/teams/:name">
            <TeamPage />
          </Route>

          <Route path="/">
            <HomePage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;

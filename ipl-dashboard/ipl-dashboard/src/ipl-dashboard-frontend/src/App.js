import './App.scss';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { TeamPage } from './pages/TeamPage';
import { MatchPage } from './pages/MatchPage';

function App() {
  return (
    <div className="App">
      <Router>
        <Switch> // switch checks selects the first matching route 
          <Route path="/teams/:teamName/matches/:year">
            <MatchPage />
          </Route>
          <Route path="/teams/:teamName">
            <TeamPage />
          </Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;

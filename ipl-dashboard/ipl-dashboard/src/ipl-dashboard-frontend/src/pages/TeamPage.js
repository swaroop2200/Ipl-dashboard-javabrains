import { React, useEffect, useState } from 'react'
import { MatchDetailCard } from '../components/MatchDetailCard'
import { MatchSmallCard } from '../components/MatchSmallCard'
import { useParams } from 'react-router-dom'
import './TeamPage.scss'

export const TeamPage = () => {

  const [team, setTeam] = useState({ matches: [] });
  const { teamName } = useParams(); //returns an object with the url params, we are destructuring and just taking the teamName
  useEffect(
    () => {
      const fetMatches = async () => {
        const response = await fetch(`http://localhost:8080/teams/${teamName}`);
        const data = await response.json();
        console.log(data);
        setTeam(data);
      };
      fetMatches();
    },
    [teamName] // u want this effect to run when the component loads
  );

  // if wrong team name was entered, then teamNmae wont be present, so we return Team Not Found
  if (!team || !team.teamName) {
    return <h1>Team Not Found</h1>
  }
  return (
    <div className="TeamPage">
      <div className="team-name-section" style={{ backgroundColor: "yellow" }}>
        <h1 className="team-name">{team.teamName}</h1>
      </div>
      <div className="win-loss-section">Wins / Losses </div>
      <div className="match-detail-section">
        <h3>Latest Matches</h3>
        <MatchDetailCard teamName={team.teamName} match={team.matches[0]} />
      </div>
      {team.matches.slice(1).map(match => <MatchSmallCard teamName={team.teamName} match={match} />)}

      <div className="more-link">
        <a href="#">More ></a>
      </div>
    </div>
  );
}

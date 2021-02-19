import './App.css';

import NavBar from './components/NavBar'
import Landing from './pages/Landing.jsx'
import AllPokemon from './pages/core/AllPokemon.jsx'
import Team from './pages/Team'

import React, { Component } from 'react'
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

export default class App extends Component {

  constructor(props) {
    super(props)
    this.state = {
      
    }
    
  }

  render() {
    return (
        <div style={{
            backgroundImage: "url(/grasslands_8bit.jpg)",
            backgroundPosition: 'center',
            backgroundSize: 'cover',
            backgroundRepeat: 'no-repeat'
            }}>
        <Router>
          <NavBar />
   
          <Switch>
  
            <Route path="/home">
                <Landing/>
            </Route>
            
            <Route path="/view-pokemon">
                <AllPokemon/>
            </Route>

            <Route path="/view-team">
              <Team />
            </Route>
  
  
            
            {/* leave this route at the bottom at all times */}
            <Route path="/">
                <Landing/>
            </Route>
            
          </Switch>
        </Router>
      </div>
    );
  }

}

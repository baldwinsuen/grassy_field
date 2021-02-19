import React, { Component } from 'react';
import * as rbs from 'react-bootstrap'

import TeamView from '../components/team/TeamView'

import CompareTeam from '../css/images/Compare-Your-Team.png';
import { user_store } from '../components/landing/user_store'

const testTeams = [
    {
        "id": 1,
        "name": "Rattatas babey",

        "pokemon": [{
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        }],

        "stats": [{
            "attack": 200,
            "defense": 100, "spatk": 50, "spdef": 111, "speed": 400
        }],

        "typeStat": [{
            "normal": 10,
            "fire": 0, "water": 0, "grass": 0, "electric": 0, "ice": 0, "fighting": 0,
            "poison": 0, "ground": 0, "flying": 0, "psychic": 0, "bug": 0, "rock": 0, "ghost": -10,
            "dark": -10, "dragon": 0, "steel": 0, "fairy": 0
        }]
    },

    {

        "id": 2,
        "name": "Ratattas 2: Electric Boogaloo",

        "pokemon": [{
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 19,
            "name": "Rattata",
            "img": 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png',
        },
        {
            "id": 20,
            "name": "Raticate",
            "img": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/20.png",
        }],

        "stats": [{
             "attack": 400,
            "defense": 4, "spatk": 2, "spdef": 111, "speed": 143
        }],

        "typeStat": [{
            "normal": 10,
            "fire": 0, "water": 0, "grass": 0, "electric": 0, "ice": 0, "fighting": 0,
            "poison": 0, "ground": 0, "flying": 0, "psychic": 0, "bug": 0, "rock": 0, "ghost": -10,
            "dark": -10, "dragon": 0, "steel": 0, "fairy": 0
        }]
    }
]


class Team extends Component {

    constructor(props) {
        super(props)

        this.state = {
            teamOneData: null,
            selectedTeamOneId: null,
            teamTwoData: null,
            selectedTeamTwoId: null,

            storeTeam: testTeams,

            // Determines if team two view is drawn
            renderSecondView: false


        }

        this.testGetTeam()
        console.log(user_store.user_id)
    }

    async testGetTeam() {
        console.log("Testing queury")
        // fetch team list based on user id
        await fetch("http://localhost:8080/grassyfield/teams/1")
            .then(data => data.json())
            .then(data => (console.log(data)))
            .catch(err => console.log(err))
    }






    /* Load the team data on mount */
    componentDidMount() {
        console.log("Starting Team Page")

        // Replace with fetch of team data in actual use
        this.setState({ storeTeam: testTeams })



    }


    handleSelectedTeamChange = (event) => {
        // update the selected id
        this.setState({ selectedTeamOneId: event.target.value })

        // update the the team data based on the selected team
        this.setState({
            teamOneData:
                (testTeams.filter(team => { return team.id == event.target.value }))[0] // find the team that matches the selected id
        },
            () => { console.log(this.state.teamOneData) })
    }

    handleSelectedTeamTwoChange = (event) => {
        // update the selected id
        this.setState({ selectedTeamTwoId: event.target.value })

        // update the the team data based on the selected team
        this.setState({
            teamTwoData:
                (testTeams.filter(team => { return team.id == event.target.value }))[0] // find the team that matches the selected id
        },
            () => { console.log(this.state.teamTwoData) })
    }


    render() {


        let renderOfSecondTeam;

        // if the second view is to be rendered, generate the JSX
        if (this.state.renderSecondView) {
            renderOfSecondTeam = (
                <rbs.Col>
                    <rbs.Container fluid>
                        <rbs.Card>
                            <TeamView
                                selectedTeam={this.state.selectedTeamTwoId}
                                handleSelectedTeamChange={this.handleSelectedTeamTwoChange}
                                teamData={this.state.teamTwoData}
                                storeTeam={this.state.storeTeam}
                            />

                        </rbs.Card>

                    </rbs.Container>

                </rbs.Col>
            )
        } else {
            renderOfSecondTeam = <div></div>
        }



        // If logged in, return this
        return (
            <div>
                
                <center><img src = {CompareTeam}/></center>
                <rbs.Row md={6}>
                    <rbs.Container>
                        <rbs.Col>
                            <rbs.Button onClick={() => { this.setState({ renderSecondView: !this.state.renderSecondView }) }}>Toggle Team Two View</rbs.Button>
                        </rbs.Col>
                    </rbs.Container>
                </rbs.Row>


                <rbs.Row>
                    <rbs.Col>
                        <rbs.Container fluid>

                            {/* Team viewer component */}
                            <rbs.Card>
                                <TeamView
                                    selectedTeam={this.state.selectedTeamOneId}
                                    handleSelectedTeamChange={this.handleSelectedTeamChange}
                                    teamData={this.state.teamOneData}
                                    storeTeam={this.state.storeTeam}
                                />
                            </rbs.Card>


                            {/* Optional second Team viewer component for comparison */}
                        </rbs.Container>
                    </rbs.Col>


                    {/* Depending on renderSecondTeam state, show view of team two*/}
                    {renderOfSecondTeam}

                </rbs.Row >
            </div>
        )
    }
}

export default Team
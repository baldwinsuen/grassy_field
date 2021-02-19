import React, { Component } from 'react';
import * as rbs from 'react-bootstrap'
import TeamDisplayPokemon from './TeamDisplayPokemon';

import TeamMenu from './TeamMenu'
import TeamStats from './TeamStats';

class TeamView extends Component {
    constructor(props) {
        super(props)
    }



    render() {
        let renderCard;

        if (this.props.teamData != null) {
            renderCard = <TeamDisplayPokemon teamData={this.props.teamData} />
        } else {
            renderCard = <h2>Please select a team.</h2>
        }

        return (

            <rbs.Row>
                {/* pass in prop with available teams */}
                {/* Display teams for user */}
                <rbs.Col md={4}>
                    <rbs.Container fluid>


                        <rbs.Col>
                            <TeamMenu
                                handleSelectedTeamChange={this.props.handleSelectedTeamChange}
                                storeTeam={this.props.storeTeam} />
                        </rbs.Col>
                    </rbs.Container>

                </rbs.Col>

                {/* Display pokemans */}
                <rbs.Col md={5}>
                    <rbs.Container fluid>
                        <div>
                            <rbs.Col>
                                {/* Examine rendercard above return call */}
                                {renderCard}


                            </rbs.Col>
                            {/* Display Stats */}

                        </div >

                        {/*  */}

                    </rbs.Container>

                    <rbs.Col>
                    <rbs.Container fluid>
                        <TeamStats teamData={this.props.teamData} />
                    </rbs.Container>
                </rbs.Col>

                </rbs.Col>
                
            </rbs.Row>

        )
    }
}

export default TeamView;
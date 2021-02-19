import React, { Component } from 'react';
import * as rbs from 'react-bootstrap'
import TeamPokemonCard from './TeamPokemonCard';



class TeamDisplayPokemon extends Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <rbs.Container className="grid">
                {/* Iterate through the pokemon in the party and make cards*/}
                {this.props.teamData.pokemon.map((pokemon, index) => {
                    return (
                        <TeamPokemonCard pokemonData={pokemon} boxStyle={"box" + String(index + 1)} />
                    )
                })
                }

            </rbs.Container>
        )
    }
}

export default TeamDisplayPokemon
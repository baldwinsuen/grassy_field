import React, { Component } from 'react';

import * as rbs from 'react-bootstrap'

import '../../css/box.css'

const TeamPokemonCards = (props) => {
    return (
        <div className="box box1">
            
            <rbs.Card>
                <rbs.Card.Img variant="top" className="cardImage" src={props.pokemonData.img} />
                <rbs.Card.Body>
                <rbs.Card.Title>  {props.pokemonData.name} </rbs.Card.Title>
                
                {props.pokemonData.id}
                </rbs.Card.Body>
            </rbs.Card>
        </div >
    )
}

export default TeamPokemonCards
import React, { Component, useState } from 'react'
import * as rbs from 'react-bootstrap'
import { view } from 'react-easy-state'
import { store, getRandomPokemon } from '../components/pokemon_store'
import Signup from '../components/landing/SignupModal.jsx'
import Login from '../components/landing/LoginModal.jsx'
import '../css/landing.css'
import GrassyField from '../css/images/Grassy-Field.png';

class Landing extends Component {

  constructor(props) {
    super(props)
    
    this.state = {
    //   username: "",
    //   password: ""
    }
    this.handleUsername = this.handleUsername.bind(this)
  }

  handleUsername = (e) => {
    this.setState({
      username: e.target.value
    })
    
  }

//   setLogInState = () => {
//     if 
//   }



  componentDidMount() {
      getRandomPokemon()
      this.interval = setInterval(() => {
          getRandomPokemon()
      }, 100000);
  }


  render() {
      return (
          <rbs.Container fluid>
              <center><img src={GrassyField} /></center>
              <br></br>
              <rbs.Row>
                  <rbs.Col md={2}>
                  </rbs.Col>

                  <rbs.Col md={8}>

                      <rbs.Row>
                          <rbs.Col md={4}></rbs.Col>
                          <rbs.Col md={4}>
                              <rbs.Jumbotron style={{ background: "linear-gradient(to bottom, #dc3545 0, #dc3545 46%, #000000 50%, #f8f9fa 54%, #f8f9fa 100%)"}}>
                                  <center>
                                      <Signup></Signup>
                                  </center>
                                  <br></br>
                                  <center>
                                      <Login></Login>
                                  </center>
                              </rbs.Jumbotron>

                              <rbs.Jumbotron>
                                  <center><h4>Featured Pokemon</h4></center>
                                  {(store.landing_pokemon.length < 1 || store.landing_pokemon == undefined) ? 
                                      <div></div> :
                                      <center>
                                        <p><strong>{store.landing_pokemon.name.charAt(0).toUpperCase() + store.landing_pokemon.name.slice(1)}</strong></p>
                                      <img className="landingPokemon" src={store.landing_pokemon.sprites.other["official-artwork"].front_default}></img>
                                      </center>
                                  }
                              </rbs.Jumbotron>
                          </rbs.Col>
                          <rbs.Col md={4}></rbs.Col>
                      </rbs.Row>
                  </rbs.Col>
                  <rbs.Col md={2}>
                  </rbs.Col>
              </rbs.Row>
          </rbs.Container>
      )
  }
}
export default view(Landing)

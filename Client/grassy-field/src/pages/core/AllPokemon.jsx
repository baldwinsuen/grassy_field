import React, { Component } from 'react'
import { view } from 'react-easy-state'
import { MDBDataTableV5 } from 'mdbreact';
import * as rbs from 'react-bootstrap'

import { store, getAllPokemon } from '../../components/pokemon_store'
import { user_store } from '../../components/landing/user_store'

import ViewPokemon from '../../css/images/View-Pokemon.png';

// @TODO add button to add to team
class AllPokemon extends Component {

    constructor(props) {
        super(props)
        this.state = {
            show: false,
            team_name: ""
        }

        this.handleNewSelect = this.handleNewSelect.bind(this)
        this.getStats = this.getStats.bind(this)
        this.getTypes = this.getTypes.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.handleShow = this.handleShow.bind(this)
        this.addPokemon = this.addPokemon.bind(this)
        this.displayPokemon = this.displayPokemon.bind(this)
        this.clearPokemon = this.clearPokemon.bind(this)
        this.handleTeamName = this.handleTeamName.bind(this)

    }

    handleTeamName = (e) => {
        this.setState({
            team_name: e.target.value
        })
    }

    componentDidMount() {
        getAllPokemon()
    }

    getColumns() {
        const pokemonTableColumns = [
            { label: 'Pokedex #', field: 'id', width: 20},
            { label: 'Sprite', field: 'sprite', width: 90},
            { label: 'Name', field: 'name', width: 90, attributes: {'aria-controls': 'DataTable', 'aria-label': 'Name',} },
            { label: 'Type', field: 'type', width: 90},
            { label: 'Base HP', field: 'basehp', width: 50},
            { label: 'Base Attack', field: 'baseattack', width: 50},
            { label: 'Base Defense', field: 'basedefense', width: 50},
            { label: 'Base Special Attack', field: 'base_special_attack', width: 50},
            { label: 'Base Special Defense', field: 'base_special_defense', width: 50},
            { label: 'Speed ', field: 'basespeed', width: 50},
            { label: 'Height', field: 'height', width: 90},
            { label: 'Weight', field: 'weight', width: 90},
            { label: 'Select', field: 'select', width: 90}
        ]
        return pokemonTableColumns
    }

    getRows() {
        const ret = []
        const poke = store.pokemon_data.map(entry => {
            var ename = entry.name.charAt(0).toUpperCase() + entry.name.slice(1)
            var types = this.getTypes(entry, entry.types.length)
            var stats = this.getStats(entry.stats)

            ret.push(
                {
                    id: entry.id,
                    sprite: <img src={entry.sprites.front_default}></img>,
                    name: ename, 
                    type: types,
                    basehp: <center>{stats[0]}</center>,
                    baseattack: <center>{stats[1]}</center>,
                    basedefense: <center>{stats[2]}</center>,
                    base_special_attack: <center>{stats[3]}</center>,
                    base_special_defense: <center>{stats[4]}</center>,
                    basespeed: <center>{stats[5]}</center>,
                    height: <center>{entry.height}</center>, 
                    weight: <center>{entry.weight}</center>,
                    select: <center><rbs.Button value={entry.id} onClick={this.handleNewSelect}>Select</rbs.Button></center>
                }
            )
        })

        return ret
    }

    handleNewSelect = (event) => {
        store.pokemon_data.filter(function(poke) {
            if(poke.id == event.target.value) {
                if(store.team_pokemon.length < 6) {
                    var ename = poke.name.charAt(0).toUpperCase() + poke.name.slice(1)

                    var typeList = []
                    poke.types.forEach(a => typeList.push(a.type.name))

                    var statsList = []
                    poke.stats.forEach(index => statsList.push(index.base_stat))
                    
                    var obj = {
                        "id": poke.id,
                        "name": ename,
                        "type": typeList,
                        "basestats": statsList
                    }
                    store.team_pokemon.push(obj)
                }
                else {
                    console.log("There are 6 pokemon in your team already.")
                }
            }
        })
    }

    getStats(entry) {
        const obj = []
        entry.forEach(index => {
            obj.push(index.base_stat)
        })
        return obj
    }

    getTypes (entry, indexes) {
        var types = ""
        var indexes = entry.types.length
        entry.types.forEach(entry => {
            if(indexes === 2) {
                types = types + '/' + entry.type.name.charAt(0).toUpperCase() + entry.type.name.slice(1)
            }
            else {
                types = entry.type.name.charAt(0).toUpperCase() + entry.type.name.slice(1)
            }
        })

        if(indexes === 2) {
            types = types.slice(1, types.length)
        }
        return types
    }

    handleClose = () => {
        this.setState({
            show: false
        })
    }

    handleShow = () => {
        this.setState({
            show: true
        })
    }

    addPokemon = () => {
        const pokemon = Object.assign({}, store.team_pokemon)
        const a = JSON.parse(JSON.stringify(pokemon))
        if(this.state.team_name === "") {
            this.setState({
                team_name: "Team"
            })
        }
        
        if(Object.keys(a).length === 6) {
            fetch('http://localhost:8080/grassyfield/create_team/1'  , {
                method: 'POST',
                headers: { 
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                body: JSON.stringify({
                    "pokemon1": a[0],
                    "pokemon2": a[1],
                    "pokemon3": a[2],
                    "pokemon4": a[3],
                    "pokemon5": a[4],
                    "pokemon6": a[5],
                    "teamName": this.state.team_name,
                    "trainerName": user_store.username
                    
                })
            })
            .then(response => {
                if(response.status === 200) {
                    console.log("Team was created successfully")
                    this.clearPokemon()
                    return response.json()
                }
                else {
                    return response
                }
            })
            .then(response => {
                console.log(response)
            })
            .catch(err => console.log(err))
        }
        this.handleClose()
    }

    displayPokemon = () => {
        const pokemon = Object.assign({}, store.team_pokemon)
        const a = JSON.parse(JSON.stringify(pokemon))
        var listItems = []
        for(var i = 0; i < Object.keys(a).length; i++){
            listItems.push(
                <li>{a[i].name}</li>
            )
        }
        return (
            <ul>
                {listItems}
            </ul>
        )
    }

    clearPokemon = () => {
        store.team_pokemon = []
    }

    render() {
        const data = {
            columns: this.getColumns(),
            rows: this.getRows()
        }
        return (
            <div>
                <br></br>
                <center><img src={ViewPokemon} /></center>
                <br></br>
                <div>
                    <rbs.Button variant="primary" onClick={this.handleShow} className="float-right" style={{"margin-right": "20px"}}>
                        Checkout Pokemon
                    </rbs.Button>
  
                    <rbs.Modal 
                        show={this.state.show} 
                        onHide={this.handleClose}
                        backdrop="static"
                    >
                        <rbs.Modal.Header closeButton>
                            <rbs.Modal.Title>Pokemon</rbs.Modal.Title>
                        </rbs.Modal.Header>
                        <rbs.Modal.Body>
                            {store.team_pokemon.length < 1 ? 
                            <p>There are no pokemon added to the cart.</p> : 
                            this.displayPokemon()
                            }
                            <rbs.Form>
                                <rbs.Form.Group controlId="formBasicEmail">
                                    <rbs.Form.Label>Team Name</rbs.Form.Label>
                                    <rbs.Form.Control type="text" placeholder="Enter Team Name" value={this.state.team_name} onChange={this.handleTeamName}/>
                                </rbs.Form.Group>
                            </rbs.Form>
            
                        </rbs.Modal.Body>
                        <rbs.Modal.Footer>
                            <rbs.Button variant="secondary" onClick={this.handleClose}>
                                Close
                            </rbs.Button>
                            <rbs.Button variant="warning" onClick={this.clearPokemon}>
                                Clear
                            </rbs.Button>
                            <rbs.Button variant="primary" onClick={this.addPokemon}>
                                Add Pokemon
                            </rbs.Button>
                        </rbs.Modal.Footer>
                    </rbs.Modal>
                </div>
                <rbs.Container fluid>
                    <rbs.Row>
                        <rbs.Col md={1}></rbs.Col>
                        <rbs.Col md={10}>
                            <MDBDataTableV5 
                                hover 
                                entriesOptions={[4, 8, 10]} 
                                entries={4} 
                                pagesAmount={4} 
                                data={data}
                                theadColor={"red lighten-1"}
                                tbodyColor={"white"}
                                fullPagination
                                bordered
                            />
                        </rbs.Col>
                        <rbs.Col md={1}></rbs.Col>
                    </rbs.Row>
                </rbs.Container>
                
            </div>
        )
    }

}

export default view(AllPokemon)
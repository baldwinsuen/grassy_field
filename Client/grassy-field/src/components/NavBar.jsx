  
import React, { Component } from 'react'
import { Navbar, NavDropdown, Nav } from 'react-bootstrap'
import { BrowserRouter as Router, Link } from "react-router-dom";

import { user_store } from './landing/user_store.js'

export default class NavBar extends Component {
    
    
    render() {
        return (
            <div className="App">
                <Navbar collapseOnSelect expand="xl" bg="danger" variant="dark">
                    <Navbar.Brand href="/home">Grassy</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                        
                        <Nav className="justify-content-end" style={{ width: "100%" }}>
                        
                        {user_store.logged_in ? 
                            <Nav.Link><b>Logged In: {user_store.username}</b></Nav.Link>
                            :
                            <div></div>
                            }
                            
                            <Nav.Link as={Link} to="/home">
                                Home
                            </Nav.Link>
                            <Nav.Link as={Link} to="/view-pokemon">
                                View Pokemon
                            </Nav.Link>
                            <Nav.Link as={Link} to="/view-team">
                                View Teams
                            </Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
            </div>
        )
    }
}
import React, { Component, useState } from 'react'
import { view } from 'react-easy-state'
import * as rbs from 'react-bootstrap'

import { store, createUser } from './user_store'

class Signup extends Component {
    constructor(props) {
        super(props)
        this.state = {
            show: false,
            username: "",
            password: ""
        }

        this.handleClose = this.handleClose.bind(this)
        this.handleShow = this.handleShow.bind(this)
        this.handleUsername = this.handleUsername.bind(this)
        this.handlePassword = this.handlePassword.bind(this)
        this.submitUser = this.submitUser.bind(this)
    }

    handleUsername = (e) => {
        this.setState({
          username: e.target.value
        })
    }

    handlePassword = (e) => {
        this.setState({
            password: e.target.value
        })
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

    submitUser = () => {
        createUser(this.state.username)
        this.handleClose()
    }


    render() {
        return (
            <>
                <rbs.Button variant="primary" onClick={this.handleShow}>
                    Sign Up
                </rbs.Button>
  
                <rbs.Modal 
                    show={this.state.show} 
                    onHide={this.handleClose}
                    backdrop="static"
                >
                    <rbs.Modal.Header closeButton>
                        <rbs.Modal.Title>Sign Up</rbs.Modal.Title>
                    </rbs.Modal.Header>
                    <rbs.Modal.Body>
        
                        <rbs.Form>
                            <rbs.Form.Group controlId="formBasicEmail">
                                <rbs.Form.Label>Username</rbs.Form.Label>
                                <rbs.Form.Control type="text" placeholder="Enter username" value={this.state.username} onChange={this.handleUsername}/>
                            </rbs.Form.Group>
                
                            {/* <rbs.Form.Group controlId="formBasicPassword">
                                <rbs.Form.Label>Password</rbs.Form.Label>
                                <rbs.Form.Control type="password" placeholder="Password" value={this.state.password} onChange={this.handlePassword}/>
                            </rbs.Form.Group> */}
                            
                        </rbs.Form>
        
                    </rbs.Modal.Body>
                    <rbs.Modal.Footer>
                        <rbs.Button variant="secondary" onClick={this.handleClose}>
                        Close
                        </rbs.Button>
                        <rbs.Button variant="primary" onClick={this.submitUser}>
                        Sign Up
                        </rbs.Button>
                    </rbs.Modal.Footer>
            </rbs.Modal>
      </>
    )}
}
export default view(Signup)
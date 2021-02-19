import React, { Component } from 'react';
import { Form } from 'react-bootstrap'

class TeamMenu extends Component {

    constructor(props) {
        super(props)

        
    }

    render() {
        return (
            <form>
                <Form.Group controlId="exampleForm.ControlSelect2">
                    <Form.Label>Select Team</Form.Label>
                    <Form.Control
                        as="select"
                        style={{ height: "80vh" }}
                        multiple
                        onChange={this.props.handleSelectedTeamChange}>
                        
                        {/* From the store of teams fetched, display the user's teams as options */}
                        {
                            this.props.storeTeam.map(team => {
                                return (
                                    <option value={team.id}>{team.name}</option>
                                )
                            })
                        }

                        {/* Generate from passed in team */}
                    </Form.Control>
                </Form.Group>
            </form>
        )
    }
}

export default TeamMenu
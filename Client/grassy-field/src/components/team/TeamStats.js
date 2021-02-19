import React, { Component } from 'react';
import * as rbs from 'react-bootstrap'
 import { PieChart } from 'react-d3-components'

class TeamStats extends Component {
    constructor(props) {
        super(props)

        // selected team passed in as prop
        
    }

    getStatChart() {
        const pieChartData = {
            label: "Total stats of team",
            values: []
        }


        Object.keys(this.props.teamData.stats[0]).forEach((stat) => {

            const xValue = stat
            const yValue = this.props.teamData.stats[0][stat]

            pieChartData.values.push({ x: xValue, y: yValue })
        })

        return (
            <div>
                <h2>Team Statistics</h2>
                <PieChart
                    data={pieChartData}
                    width={425}
                    height={300}
                    margin={{ top: 10, bottom: 10, left: 100, right: 100 }}
                />
            </div>)
    }

    render() {
        let renderStats;

        if (this.props.teamData != null) {
            renderStats = this.getStatChart()


        } else {
            renderStats = <h2>Please select a team.</h2>
        }



        return (
            <rbs.Container>
                {renderStats}
            </rbs.Container>
        )
    }
}

export default TeamStats

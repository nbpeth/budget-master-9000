import React from 'react';
import store from './expensesStore.js';
import { loadStatsAction } from './actions.js';


class WeeklySummaryWidget extends React.Component {
    constructor(props){
        super(props);
    }

    componentWillMount(){
        store.dispatch(loadStatsAction());
    }

    componentDidMount() {
        store.subscribe(() => {
            var state = store.getState();
            this.setState({
                stats:state.stats
            });
        });
    }

    determineCellColor(value){
        return value && value >= 0 ? "bg-success" : "bg-danger";
    }

    render() {
        var stats = store.getState().stats;

        var weekExpenses;
        var weeklyRollup;
        var weeklyRows = [];
        var remainderCellClass = "bg-warn";
        
        if(stats && stats.weekExpenses && stats.weeklyRollup){
            weekExpenses = stats.weekExpenses;
            weeklyRollup = stats.weeklyRollup;
//stats.weeklyRollup.filter((x) => x.sum)
            remainderCellClass = this.determineCellColor(300 - weekExpenses);
            weeklyRollup.filter((week) => week.sum).forEach((week) => {
                weeklyRows.push(
                    <tr key={week.weekStart}>
                        <td>
                            {week.weekStart}
                        </td>
                        <td>
                            {week.weekEnd}
                        </td>
                        <td className={this.determineCellColor(week.sum)}>
                            {week.sum}
                        </td>
                    </tr>);
                })
        }
        return(
            <div>
                <table className="table table-striped table-bordered table-hover table-inverse" id="expenseTable">
                    <tbody>
                        <tr>
                            <td className="bg-primary">
                                Weekly Budget
                            </td>
                            <td>
                                $300
                            </td>
                        </tr>

                        <tr>
                            <td className="bg-primary">
                                Spent
                            </td>
                            <td>
                                ${weekExpenses}
                            </td>
                        </tr>
                        <tr>
                            <td  className="bg-primary">
                                Remaining
                            </td>
                            <td className={remainderCellClass}>
                                ${weekExpenses ? (300 - parseFloat(weekExpenses)) : 0 }
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
                <table className="table table-striped table-bordered table-hover table-inverse" id="weeklyRollupTable">
                    <tbody>
                        <tr><td className="bg-primary">Start</td><td className="bg-primary">Stop</td><td className="bg-primary">Total</td></tr>
                        { weeklyRows ? weeklyRows : null }
                    </tbody>
                </table>
            </div>
        );
    }
}

export default WeeklySummaryWidget;
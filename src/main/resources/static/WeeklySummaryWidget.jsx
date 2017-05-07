import React from 'react';
import store from './expensesStore.js';
import { loadStatsAction } from './actions.js';
import PieChart from 'react-minimal-pie-chart';

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

    determineCellColor(qualifier){
        return qualifier ? "bg-success" : "bg-danger";
    }

    isUnderBudget(value, limit){
        return value && limit && value <= limit;
    }

    isAboveOrEqualToZero(value){
        console.log(value, value && value >= 0, value >= 0);
        return value && value >= 0;
    }

    render() {
        var stats = store.getState().stats;
        var weeklyLimit = 300;
        var weekExpenses;
        var weeklyRollup;
        var expenseRemainder = 0;
        var weeklyRows = [];
        var pie;
        var pieSlices = [];
        
        if(stats && stats.weekExpenses){
            weekExpenses = stats.weekExpenses.toFixed(2);
            expenseRemainder = weeklyLimit - parseFloat(weekExpenses).toFixed(2);
        }

        if(stats && stats.pie){

            pie = stats.pie;
   
            pie.forEach((slice => {
                console.log(slice);
                pieSlices.push({label:slice.label, value:slice.value, color: slice.color});
                
            }));
            
        }

        if(stats && stats.weeklyRollup){
            weeklyRollup = stats.weeklyRollup;

            weeklyRollup.filter((week) => week.sum).forEach((week) => {
                const sum = week.sum.toFixed(2);
                weeklyRows.push(
                    <tr key={week.weekStart}>
                        <td>
                            {week.weekStart}
                        </td>
                        <td>
                            {week.weekEnd}
                        </td>
                        <td className={this.determineCellColor(this.isUnderBudget(week.sum, weeklyLimit))}>
                           ${sum}
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
                            <td className={this.determineCellColor(this.isAboveOrEqualToZero(expenseRemainder))}>
                                ${expenseRemainder ? expenseRemainder.toFixed(2) : null}
                            </td>
                        </tr>
                        
                    </tbody>
                </table>

                <div className="bg-info">
                    
                <PieChart data={pieSlices}/>
                </div>
                <p/>
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
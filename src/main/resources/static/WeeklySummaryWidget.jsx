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

    render() {
        var stats = store.getState().stats;

        var weekExpenses;
        var remainderCellClass = "bg-warn";
        
        if(stats && stats.weekExpenses){
            weekExpenses = stats.weekExpenses;
            remainderCellClass = 300 - weekExpenses >= 0 ? "bg-success" : "bg-danger";
            
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
            </div>
        );
    }
}

export default WeeklySummaryWidget;
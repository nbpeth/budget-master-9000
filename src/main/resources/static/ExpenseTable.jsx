import Expense from './Expense.jsx';
import store from './expensesStore.js';
import { loadDataAction } from './actions.js';

class ExpenseTable extends React.Component {
    constructor(props){
        super(props);
    }
    componentWillMount() {
        store.subscribe(() => {
            var state = store.getState();
            this.setState({
                expenses:state.expenses
            });
        });
    }
    
    componentDidMount(){
        store.dispatch(loadDataAction());
    }
    
    render() {    
        var rows = []; 
        var expenses = store.getState().expenses;

        if(expenses.length > 0){
            var n=0;
            expenses.forEach((expense) => {
                rows.push(<Expense expense={expense} key={n++}/>);
            });
        }   
        return(
            <table className="table table-striped" id="expenseTable">
                <thead className="bg-info">
                    <tr>
                        <td><h3>Location</h3></td>
                        <td><h3>Cost</h3></td>
                        <td><h3>Type</h3></td>
                        <td><h3>Description</h3></td>
                        <td><h3>Day</h3></td>
                        <td><h3>Date</h3></td>
                        <td><h3></h3></td>
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        );
    }
};

export default ExpenseTable;
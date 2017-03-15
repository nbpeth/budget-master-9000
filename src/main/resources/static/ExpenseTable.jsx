import getExpenses from './expenseService.js';
import Expense from './Expense.jsx';

class ExpenseTable extends React.Component {
    constructor(props){
        super(props);
        this.state = {expenses:[]};
    }
    componentDidMount(){
        getExpenses(this);
        
    }

    render() {    
        var rows = []; 
        if(this.state.expenses){
            var n=0;
            this.state.expenses.forEach((expense) => {
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
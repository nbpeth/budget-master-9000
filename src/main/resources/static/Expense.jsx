import store from './expensesStore.js';
import { deleteExpenseAction } from './actions.js';

class Expense extends React.Component {
    constructor(props){
        super(props);
    }
    deleteExpense(id){
        store.dispatch(deleteExpenseAction(id));
    };

    render (){
        const date = new Date(this.props.expense.expenseDate);
        const formattedDate = date.getDate();

        return(
            <tr>
                <td>{this.props.expense.location}</td>
                <td>${this.props.expense.cost}</td>
                <td>{this.props.expense.expenseType}</td>
                <td>{this.props.expense.description}</td>
                <td>{this.props.expense.dayOfWeek}</td>
                <td>{formattedDate}</td>
                
                <td><button className="btn btn-danger" onClick={ () => {this.deleteExpense(this.props.expense.id)}}>Delete</button></td>

            </tr>
        );
    }
};

export default Expense;
import React from 'react';
import store from './expensesStore.js';
import { deleteExpenseAction, loadStatsAction } from './actions.js';

class Expense extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            deleteConfirmed:false,
        };
    }

    handleClick(id){
        if(this.state.deleteConfirmed){
            store.dispatch(deleteExpenseAction(id));

            this.setState({deleteConfirmed: false});
        }
        else{
            this.setState({deleteConfirmed: true});
        }
        this.forceUpdate();
    }

    render (){
        const deleteConfirmed = this.state.deleteConfirmed;
        var buttonClass = deleteConfirmed ? "btn btn-danger" : "btn btn-success";
        var buttonLanguage = deleteConfirmed ? "Confirm?" : "Delete";

        return(
            <tr>
                <td>{this.props.expense.location}</td>
                <td>${this.props.expense.cost}</td>
                <td>{this.props.expense.expenseType}</td>
                <td>{this.props.expense.expenseDate}</td>
                
                <td>
                    <button className={buttonClass} onClick={ () => { this.handleClick(this.props.expense.id)} }>{ buttonLanguage }</button>
                </td>

            </tr>
        );
    }
};


export default Expense;
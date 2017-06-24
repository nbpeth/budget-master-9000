import React from 'react';
import store from './expensesStore.js';
import { loadRecurringExpenseAction, deleteRecurringExpenseAction, enableRecurringExpenseEditingAction, disableRecurringExpenseEditingAction, updateRecurringExpenseAction, createRecurringExpenseAction } from './actions.js';


class RecurringExpenseReport extends React.Component {
    constructor(props){
        super(props);
    }
    render(){
        return(
            <div>
                <div className="container">
                    <Navigation/>
                    <RecurringExpenseTable /> 
                </div>
            </div>
        );
    }
}

class RecurringExpenseTable extends React.Component {
    constructor(props){
        super(props);
    }

    componentDidMount(){
        store.dispatch(loadRecurringExpenseAction());

        store.subscribe(() => {
            this.setState({
                recurringExpenses:store.getState().recurringExpenses
            });
        });
        
    }

    render(){
        var rows = []; 
        var recurringExpenses = store.getState().recurringExpenses;
        var sumExpenses = 0;

        if(recurringExpenses && recurringExpenses.length > 0){
            var n=1;
            recurringExpenses.forEach((recurringExpense) => {
                rows.push(<RecurringExpense recurringExpense={recurringExpense} key={n++}/>);
                sumExpenses += recurringExpense.cost;
            });
        } 
        return(
            <table className="table table-striped table-hover" id="recurringExpenseTable">
                <thead className="bg-info">
                    <tr>
                        <td><h3>Name</h3></td>
                        <td><h3>Cost</h3></td>
                        <td><h3>Description</h3></td>
                        <td><h3>Rate</h3></td>
                        <td><h3>Action</h3></td>
                    </tr>
                    
                    <tr>
                        <td>
                        </td>
                        <td className="bg-danger text-center">
                            <h4>
                                ${sumExpenses}
                            </h4>
                        </td>  
                        <td></td>
                        <td></td>
                        <td></td>  
                    </tr>
                
                </thead>
                    {rows}
            </table>
        );
    }
}

class RecurringExpense extends React.Component {
    constructor(props){
        super(props);
    }

    componentDidMount(){
        store.subscribe(() => {
            var state = store.getState();
        });
    }

    render(){
        const enableEditing = store.getState().enableRecurringExpenseEditing.indexOf(this.props.recurringExpense.id) > -1;
        return(
            <tbody>
                {enableEditing ? <EditRecurringExpenseCells recurringExpense={this.props.recurringExpense}/> : <RecurringExpenseCells recurringExpense={this.props.recurringExpense}/>}
            </tbody>
        );
    }
}

class RecurringExpenseCells extends React.Component {
    constructor(props){
        super(props);
    }

    handleClick(id){
        store.dispatch(enableRecurringExpenseEditingAction(id));
    }

    render(){
        var id = this.props.recurringExpense.id;
        return(
           <tr>
                <td>{this.props.recurringExpense.name}</td>
                <td className="bg-warning text-center">${this.props.recurringExpense.cost}</td>
                <td>{this.props.recurringExpense.description}</td>
                <td>{this.props.recurringExpense.span}</td>
                <td>{<button key = "edit" className="btn btn-warning " onClick={ () => { this.handleClick(id)} }>Edit</button>} </td>
            </tr>
        );
    }
}

class EditRecurringExpenseCells extends React.Component {
    constructor(props){
        super(props);
    }

    handleClick(data){
        if(data){
            store.dispatch(updateRecurringExpenseAction(data));
        }
        store.dispatch(disableRecurringExpenseEditingAction(this.props.recurringExpense.id));
    }

    handleDelete(id){
        store.dispatch(deleteRecurringExpenseAction(id));
    }

    render(){
        const cellId = this.props.recurringExpense.id;

        const getFormData = () => {
			var name = document.getElementById("name_"+cellId).value;
			var cost = document.getElementById("cost_"+cellId).value;
			var span = document.getElementById("span_"+cellId).value;
			var description = document.getElementById("description_"+cellId).value;

			return {
				name: name,
				cost: cost,
				description: description, 
				span: span,
                id: cellId
			};
		};
        const saveButton = <button key = "save" className="btn btn-info " onClick={ () => { this.handleClick(getFormData())} }>Save</button>;
        const cancelButton = <button key = "cancel" className="btn btn-warning " onClick={ () => { this.handleClick()} }>Cancel</button>
        const deleteButton = <button key = "delete" className="btn btn-danger " onClick={ () => { this.handleDelete(cellId)} }>Delete</button>

        return(
            <tr>
                <td><input type="text" id={"name_"+cellId} className="form-control" defaultValue={this.props.recurringExpense.name}/></td>
                <td><input type="number" id={"cost_"+cellId} className="form-control" defaultValue={this.props.recurringExpense.cost}/></td>
                <td><input type="text" id={"description_"+cellId} className="form-control" defaultValue={this.props.recurringExpense.description ? this.props.recurringExpense.description : ""}/></td>
                <td><input type="text" id={"span_"+cellId} className="form-control" defaultValue={this.props.recurringExpense.span}/></td>
                <td>{[saveButton, cancelButton, deleteButton]}</td>
            </tr>
        );
    }
}

class Navigation extends React.Component{
    render(){
        const showForm = store.getState().showForm

        const handleClick = () => {  
            store.dispatch(createRecurringExpenseAction());
        };
        return(
            <div>
                <nav className="navbar">
                    <form className="form-inline">
                        <p/>
                        <button className="btn-link" type="button" onClick={ () => { handleClick() }}>New Recurring Expense</button>
                    </form>
                </nav>
            </div>
        );
    };
};

export default RecurringExpenseReport;
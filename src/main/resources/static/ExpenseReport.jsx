import React from 'react';
import ExpenseTable from './ExpenseTable.jsx';
import CreateExpenseForm from './CreateExpenseForm.jsx';
import WeeklySummaryWidget from './WeeklySummaryWidget.jsx';
import store from './expensesStore.js';
import { toggleForm, loadDataAction } from './actions.js';

class ExpenseReport extends React.Component{
    constructor(props){
        super(props);
    }
    componentWillMount() {
        store.subscribe(() => {
            var state = store.getState();
            this.setState({
                showForm:state.showForm
            });
        });
    };
    componentDidMount(){
        store.dispatch(loadDataAction());
    };
    render () {
        const showForm = store.getState().showForm

        return(
            <div>
                <div className="container">
                    <Navigation/>
                    {showForm ? <CreateExpenseForm /> : null}
                        <div className="row">
                            <div className="col-md-3">
                                <WeeklySummaryWidget id="weekly" />
                            </div>
                            <div className="col-md-8">
                                <ExpenseTable id="table" />
                            </div>
                        </div>
                </div>
            </div>
        );
    }
};

class Navigation extends React.Component{
    render(){
        const showForm = store.getState().showForm

        const handleClick = () => {  
            store.dispatch(toggleForm());
        };
        return(
            <div>
                <nav className="navbar">
                    <form className="form-inline">
                        <p/>
                        <button className="btn-link" type="button" onClick={ () => { handleClick() }}>{showForm ? "Hide Form" : "Enter Expense"}</button>
                    </form>
                </nav>
            </div>
        );
    };
};

export default ExpenseReport;
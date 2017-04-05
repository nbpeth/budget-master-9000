import React from 'react';
import ReactDOM from 'react-dom';
import ExpenseTable from './ExpenseTable.jsx';
import CreateExpenseForm from './CreateExpenseForm.jsx';
import expenseManagerApp from './reducers.js';
import WeeklySummaryWidget from './WeeklySummaryWidget.jsx';
import store from './expensesStore.js';
import { toggleForm, loadDataAction } from './actions.js';

class App extends React.Component {
    constructor(props){
        super(props);
    }
    render(){
        return(
            <div>
                <Body />
            </div>
        );
    }
}

class Body extends React.Component{
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
                <TitleBar />
                <div className="container">
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

class TitleBar extends React.Component{
    render(){
        return(
            <div>
                <Navigation/>

                <div className="container-fluid bg bg-gradient">
                    <h1>Budget Master 9000</h1>
                </div>
            </div>
        );
    };
};

class Navigation extends React.Component{
    render(){
        const showForm = store.getState().showForm

        const handleClick = () => {  
            store.dispatch(toggleForm());
        };
        return(
                
                <nav className="navbar">
                    <form className="form-inline">
                        <p/>
                        <button className="btn-link" type="button" onClick={ () => { handleClick() }}>{showForm ? "Hide Form" : "Enter Expense"}</button>
                    </form>
                </nav>
        );
    };
};

ReactDOM.render(
  <App />, document.getElementById('root')
);


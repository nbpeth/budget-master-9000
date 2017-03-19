import ExpenseTable from './ExpenseTable.jsx';
import CreateExpenseForm from './CreateExpenseForm.jsx';
import expenseManagerApp from './reducers.js';
import store from './expensesStore.js';
import { toggleForm, loadDataAction } from './actions.js';

var App = React.createClass({
    render: function(){
        return(
            <div>
                <Body />
            </div>
        );
    }
});

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
    }
    
    componentDidMount(){
        store.dispatch(loadDataAction());
    }
    render () {
        const showForm = store.getState().showForm

        return(
            <div>
                <TitleBar />
                <Navigation />
                <div className="container">
                    {showForm ? <CreateExpenseForm /> : null}
                    <ExpenseTable id="table" />
                </div>
            </div>
        );
    }
};

class TitleBar extends React.Component{
    render(){
        return(
            <div className="container-fluid bg-primary">
	            <h1>Budget Master 9000</h1>
            </div>
        );
    }
};

class Navigation extends React.Component{
    render(){
        const showForm = store.getState().showForm

        const handleClick = () => {  
            store.dispatch(toggleForm());
        }
        return(
            <div className="container-fluid bg-success">
	            <a href="#"><h4 onClick={ () => { handleClick() }}>{showForm ? "Hide Form" : "Enter Expense"}</h4></a>
            </div>
        );
    }
}

ReactDOM.render(
  <App />, document.getElementById('root')
);


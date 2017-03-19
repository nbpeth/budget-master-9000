import ExpenseTable from './ExpenseTable.jsx';
import CreateExpenseForm from './CreateExpenseForm.jsx';
import expenseManagerApp from './reducers.js';


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
    render () {
        return(
            <div>
                <TitleBar />
                <div className="container">
                    <CreateExpenseForm />
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

ReactDOM.render(
  <App />, document.getElementById('root')
);


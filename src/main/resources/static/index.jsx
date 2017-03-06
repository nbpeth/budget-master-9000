class Expense extends React.Component {
    render (){
        return(
            <tr>
                <td>{this.props.expense.location}</td>
                <td>${this.props.expense.cost}</td>
                <td>{this.props.expense.expenseType}</td>
                <td>{this.props.expense.description}</td>
                <td>{this.props.expense.dayOfWeek}</td>
                <td>{this.props.expense.expenseDate}</td>
            </tr>
        );
    }
};

class ExpenseTable extends React.Component {
    render() {
        var rows = [];
        var n=0;
        this.props.expenses.forEach((expense) => {
            rows.push(<Expense expense={expense} key={n++}/>);
        });
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

class TitleBar extends React.Component{
    render(){
        return(
            <div className="container-fluid bg-primary">
	            <h1>Budget Master 9000</h1>
            </div>
        );
    }
};

class Body extends React.Component{
    render () {
        return(
            <div>
                <TitleBar />
                <div className="container">
                    <ExpenseTable expenses={this.props.expenses} id="table" />
                </div>
            </div>
        );
    }
};

var App = React.createClass({
    getExpenses: function() {
        var self = this;
        $.ajax({
            url: "http://localhost:8080/expenses"
        }).then(function (data) {
            self.setState({expenses: data});
        });
    },

    getInitialState: function () {
        return {expenses: []};
    },

    componentDidMount: function () {
        this.getExpenses();
    },
    render: function(){
        return(
            <Body expenses={this.state.expenses}/>
        );
    }
});

ReactDOM.render(
  <App />, document.getElementById('root')
);


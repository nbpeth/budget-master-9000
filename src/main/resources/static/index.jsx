import ExpenseTable from './ExpenseTable.jsx';

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


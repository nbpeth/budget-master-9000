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
        this.props.expenses.forEach(function(expense){
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

export default ExpenseTable;
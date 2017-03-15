class Expense extends React.Component {
    constructor(props){
        super(props)
    }
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
            </tr>
        );
    }
};

export default Expense;
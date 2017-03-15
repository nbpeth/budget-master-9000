var getExpenses = (delegate) => {
    $.ajax({
        url: "http://localhost:8080/expenses"
    }).then(function(data) {
        delegate.setState({ expenses: data });

    });
};

var submitExpense = (data, delegae) => {

};

export default getExpenses;

// module.exports = {
//     getExpenses: getExpenses(),
//     submitExpense: submitExpense
// }
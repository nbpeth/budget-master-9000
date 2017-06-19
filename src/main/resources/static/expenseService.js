const rootUri = 'http://budgetmaster9000.herokuapp.com'

export const getExpenses = () => {
    return $.ajax({
        url: rootUri + "/expenses",
        async: false
    }).responseText;
};

export const getExpensesPaged = (page) => {
    return $.ajax({
        url: rootUri + "/expenses?page=" + page + "&size=15",
        async: false
    }).responseText;
};

export const getStats = () => {
    return $.ajax({
        url: rootUri + "/expenses/stats",
        async: false
    }).responseText;
};

export const submitExpense = (data) => {

    $.ajax({
        url: rootUri + "/expenses",
        type: 'POST',
        async: false,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
};

export const deleteExpense = (id) => {
    $.ajax({
        url: rootUri + "/expenses/" + id,
        type: 'DELETE',
        async: false
    });
};
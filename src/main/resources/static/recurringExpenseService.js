const rootUri = 'http://budgetmaster9000.herokuapp.com/expenses'

export const getRecurringExpenses = () => {
    return $.ajax({
        url: rootUri + "/recurring",
        async: false
    }).responseText;
};

export const submitRecurringExpense = (data) => {

    $.ajax({
        url: rootUri + "/recurring",
        type: 'POST',
        async: false,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
};

export const deleteRecurringExpense = (id) => {
    $.ajax({
        url: rootUri + "/recurring/" + id,
        type: 'DELETE',
        async: false
    });
};

export const updateRecurringExpense = (data, id) => {
    $.ajax({
        url: rootUri + "/recurring/" + id,
        type: 'PATCH',
        async: false,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
};
export const getRecurringExpenses = () => {
    return $.ajax({
        url: "http://localhost:8080/expenses/recurring",
        async: false
    }).responseText;
};

export const submitRecurringExpense = (data) => {

    $.ajax({
        url: "http://localhost:8080/expenses/recurring",
        type: 'POST',
        async: false,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
};

export const deleteRecurringExpense = (id) => {
    $.ajax({
        url: "http://localhost:8080/expenses/recurring/" + id,
        type: 'DELETE',
        async: false
    });
};

export const updateRecurringExpense = (data, id) => {
    $.ajax({
        url: "http://localhost:8080/expenses/recurring/" + id,
        type: 'PATCH',
        async: false,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
};
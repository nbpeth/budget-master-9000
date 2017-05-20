export const getExpenses = () => {
    return $.ajax({
        url: "http://localhost:8080/expenses",
        async: false
    }).responseText;
};

export const getExpensesPaged = (page) => {
    return $.ajax({
        url: "http://localhost:8080/expenses?page=" + page + "&size=15",
        async: false
    }).responseText;
};

export const getStats = () => {
    return $.ajax({
        url: "http://localhost:8080/expenses/stats",
        async: false
    }).responseText;
};

export const submitExpense = (data) => {

    $.ajax({
        url: "http://localhost:8080/expenses",
        type: 'POST',
        async: false,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
};

export const deleteExpense = (id) => {
    $.ajax({
        url: "http://localhost:8080/expenses/" + id,
        type: 'DELETE',
        async: false
    });
};
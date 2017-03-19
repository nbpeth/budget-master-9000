export const getExpenses = () => {
    return $.ajax({
        url: "http://localhost:8080/expenses",
        async: false
    }).responseText;
};

export const submitExpense = (data) => {
    console.log("service", data);

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
export const getExpenses = () => {
    return $.ajax({
    headers: {
                origin: "http://10.0.1.9"
            },
        url: "http://localhost:8080/expenses",
        async: false
    }).responseText;
};

export const getStats = () => {
    return $.ajax({
    headers: {
                origin: "http://10.0.1.9"
            },
        url: "http://localhost:8080/expenses/stats",
        async: false
    }).responseText;
};

export const submitExpense = (data) => {
    console.log("service", data);

    $.ajax({
        headers: {
            origin: "http://10.0.1.9"
        },
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
    headers: {
                origin: "http://10.0.1.9"
            },
        url: "http://localhost:8080/expenses/" + id,
        type: 'DELETE',
        async: false
    });
};
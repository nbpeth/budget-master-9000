const rootUri = 'http://localhost:8080';
// const rootUri = 'http://budgetmaster9000.herokuapp.com';

export const login = (data) => {
    return $.ajax({
        url: rootUri + "/auth",
        type: 'POST',
        async: false,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
};
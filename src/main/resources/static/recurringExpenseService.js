import { getCookie } from './cookieService.js';
// const rootUri = 'http://localhost:8080';
const rootUri = 'http://budgetmaster9000.herokuapp.com/expenses';

export const getRecurringExpenses = () => {
    return $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
        url: rootUri + "/recurring",
        async: false
    }).responseText;
};

export const submitRecurringExpense = (data) => {

    $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
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
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
        url: rootUri + "/recurring/" + id,
        type: 'DELETE',
        async: false
    });
};

export const updateRecurringExpense = (data, id) => {
    $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
        url: rootUri + "/recurring/" + id,
        type: 'PATCH',
        async: false,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    });
};

const getTokenFromCookie = () => {
    return getCookie("token");
};
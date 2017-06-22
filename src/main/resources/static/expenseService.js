import { getCookie } from './cookieService.js';
const rootUri = 'http://budgetmaster9000.herokuapp.com';

// const rootUri = 'http://localhost:8080';

export const getExpenses = () => {
    return $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
        url: rootUri + "/expenses",
        async: false
    }).responseText;
};

export const getExpensesPaged = (page) => {
    return $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
        url: rootUri + "/expenses?page=" + page + "&size=15",
        async: false
    }).responseText;
};

export const getStats = () => {
    return $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
        url: rootUri + "/expenses/stats",
        async: false
    }).responseText;
};

export const submitExpense = (data) => {

    $.ajax({
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
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
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", getTokenFromCookie());
        },
        url: rootUri + "/expenses/" + id,
        type: 'DELETE',
        async: false
    });
};

const getTokenFromCookie = () => {
    return getCookie("token");
};
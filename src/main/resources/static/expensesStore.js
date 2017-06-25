import { createStore } from 'redux';
import expenseManagerApp from './reducers.js';
import { getCookie } from './cookieService.js';

var defaultState = {
    user: {},
    expenses: [],
    recurringExpenses: [],
    showForm: false,
    display: 'expenseReport',
    enableRecurringExpenseEditing: [],
    isLoggedIn: !getCookie("token") ? false : true,
    token: getCookie("token")
};

export default createStore(expenseManagerApp, defaultState);
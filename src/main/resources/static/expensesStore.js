import { createStore } from 'redux';
import expenseManagerApp from './reducers.js';

var defaultState = {
    expenses: [],
    recurringExpenses: [],
    showForm: false,
    display: 'expenseReport',
    enableRecurringExpenseEditing: [],
};

export default createStore(expenseManagerApp, defaultState);
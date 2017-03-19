import { createStore } from 'redux';
import expenseManagerApp from './reducers.js';

var defaultState = {
    expenses: [],
    showForm: false
};

export default createStore(expenseManagerApp, defaultState);
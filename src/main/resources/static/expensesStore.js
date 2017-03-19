import { createStore } from 'redux';
import expenseManagerApp from './reducers.js';

var defaultState = {
    expenses: []
};

export default createStore(expenseManagerApp, defaultState);
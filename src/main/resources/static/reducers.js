import { getExpenses, getStats, deleteExpense, submitExpense } from './expenseService.js';

function expenseManagerApp(state, action) {
    var newState = Object.assign({}, state);

    switch (action.type) {
        //make service calls async

        case 'LOAD_DATA':
            var expenses = parseJson(getExpenses());

            newState.expenses = expenses;

            return newState;

        case 'LOAD_STATS':
            var stats = JSON.parse(getStats());

            newState.stats = stats;

            return newState;

        case 'DELETE_EXPENSE':
            deleteExpense(action.id);

            var stats = JSON.parse(getStats());
            newState.stats = stats;
            newState.expenses = parseJson(getExpenses());

            return newState;

        case 'CREATE_EXPENSE':
            var json = JSON.stringify(action.data);
            submitExpense(json);

            var stats = JSON.parse(getStats());
            newState.stats = stats;
            newState.expenses = parseJson(getExpenses());

            return newState;

        case 'TOGGLE_FORM':
            newState.showForm = state.showForm ? false : true;

            return newState;

        default:
            return state;
    }
};

const parseJson = (data) => {
    return JSON.parse(data).content;
}

export default expenseManagerApp;
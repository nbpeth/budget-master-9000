import { getExpenses, deleteExpense, submitExpense } from './expenseService.js';

function expenseManagerApp(state, action) {
    switch (action.type) {
        case 'LOAD_DATA': //make async and safely parse response
            var newState = Object.assign({}, state);
            var expenses = getExpensesData();
            newState.expenses = expenses;

            return newState;

        case 'DELETE_EXPENSE': //probably should remove the row rather than fetch all the new rows on delete?
            var newState = Object.assign({}, state);
            deleteExpense(action.id);
            newState.expenses = getExpensesData();

            return newState;

        case 'CREATE_EXPENSE':
            var newState = Object.assign({}, state);
            var json = JSON.stringify(action.data)
            submitExpense(json);
            newState.expenses = getExpensesData();

            return newState;

        default:
            return state;
    }
};

const getExpensesData = () => {
    return JSON.parse(getExpenses());
}

export default expenseManagerApp;
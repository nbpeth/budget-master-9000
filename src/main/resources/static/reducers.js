import { getExpenses, deleteExpense, submitExpense } from './expenseService.js';

function expenseManagerApp(state, action) {
    var newState = Object.assign({}, state);

    switch (action.type) {
        //make service calls async

        case 'LOAD_DATA':
            var expenses = getExpensesData();
            newState.expenses = expenses;

            return newState;

        case 'DELETE_EXPENSE':
            deleteExpense(action.id);
            newState.expenses = getExpensesData();

            return newState;

        case 'CREATE_EXPENSE':
            var json = JSON.stringify(action.data)
            submitExpense(json);
            newState.expenses = getExpensesData();

            return newState;

        case 'TOGGLE_FORM':
            newState.showForm = state.showForm ? false : true;

            return newState;

        default:
            return state;
    }
};

const getExpensesData = () => {
    return JSON.parse(getExpenses()).content;
}

export default expenseManagerApp;
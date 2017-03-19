import { getExpenses, deleteExpense, submitExpense } from './expenseService.js';

function expenseManagerApp(state, action) {
    switch (action.type) {
        //make service calls async
        case 'LOAD_DATA':
            var newState = Object.assign({}, state);
            var expenses = getExpensesData();
            console.log(expenses);
            newState.expenses = expenses;

            return newState;

        case 'DELETE_EXPENSE':
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

        case 'TOGGLE_FORM':
            var newState = Object.assign({}, state);
            newState.showForm = state.showForm ? false : true;

            return newState;

        default:
            return state;
    }
};

const getExpensesData = () => {
    return JSON.parse(getExpenses())["content"];
}

export default expenseManagerApp;
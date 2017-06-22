import { getExpenses, getExpensesPaged, getStats, deleteExpense, submitExpense } from './expenseService.js';
import { getRecurringExpenses, deleteRecurrungExpense, submitRecurringExpense, deleteRecurringExpense, updateRecurringExpense } from './recurringExpenseService.js';
import { login } from './loginService.js';

import { getCookie, createCookie } from './cookieService.js';

function expenseManagerApp(state, action) {
    var newState = Object.assign({}, state);

    switch (action.type) {
        //make service calls async
        case 'LOAD_DATA':
            // var expenses = parseJson(getExpenses());
            var page = action.data;

            var expenses = JSON.parse(getExpensesPaged(page));

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

        case 'NAV_EXPENSE_REPORT':
            newState.display = 'expenseReport';
            console.log("expense nav", newState);
            return newState;

            //recurring expenses
        case 'UPDATE_RECURRING_EXPENSE':
            var json = JSON.stringify(action.data);
            var id = action.data.id;
            updateRecurringExpense(json, id);

            newState.recurringExpenses = JSON.parse(getRecurringExpenses());

            return newState;

        case 'LOAD_RECURRING_EXPENSE':
            newState.recurringExpenses = JSON.parse(getRecurringExpenses());

            return newState;

        case 'DELETE_RECURRING_EXPENSE':
            deleteRecurringExpense(action.data);

            newState.recurringExpenses = JSON.parse(getRecurringExpenses());

            return newState;

        case 'CREATE_RECURRING_EXPENSE':
            var json = JSON.stringify({ name: "", cost: 0, description: "", span: "monthly" });
            submitRecurringExpense(json);

            newState.recurringExpenses = JSON.parse(getRecurringExpenses());

            return newState;

        case 'NAV_RECURRING_EXPENSE_REPORT':
            newState.display = 'recurringExpenseReport';

            return newState;

        case 'ENABLE_RECURRING_EXPENSE_EDITING':
            newState.enableRecurringExpenseEditing.push(action.data);
            return newState;


        case 'DISABLE_RECURRING_EXPENSE_EDITING':
            var index = newState.enableRecurringExpenseEditing.indexOf(action.data);
            newState.enableRecurringExpenseEditing.splice(index, 1);
            return newState;

        case 'LOGIN':
            const loginJson = JSON.stringify(action.data);
            var loginResponse = login(loginJson);

            if (loginResponse.status === 200) {
                const token = JSON.parse(loginResponse.responseText).token;
                newState.token = token;

                createCookie("token", token);

                newState.isLoggedIn = true;
            }
            return newState;

        case 'LOGOUT':
            newState.token = "";
            createCookie("token", "");
            newState.isLoggedIn = false;
            return newState;


        default:
            return state;
    }
}

const parseJson = (data) => {
    return JSON.parse(data).content;
};

export default expenseManagerApp;
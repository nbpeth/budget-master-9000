export function loadDataAction(page) {
    return {
        type: "LOAD_DATA",
        data: page
    };
}

export function loadStatsAction() {
    return {
        type: "LOAD_STATS",
    };
}

export function createExpenseAction(data) {
    return {
        type: "CREATE_EXPENSE",
        data: data
    };
}

export function loadRecurringExpenseAction() {
    return {
        type: "LOAD_RECURRING_EXPENSE"
    };
}

export function deleteExpenseAction(id) {
    return {
        type: "DELETE_EXPENSE",
        id: id
    };
}

export function createRecurringExpenseAction() {
    return {
        type: "CREATE_RECURRING_EXPENSE"
    };
}

export function deleteRecurringExpenseAction(id) {
    return {
        type: "DELETE_RECURRING_EXPENSE",
        data: id
    };
}

export function toggleForm() {
    return {
        type: "TOGGLE_FORM"
    };
}

export function navExpenseReport() {
    return {
        type: "NAV_EXPENSE_REPORT"
    };
}

export function navRecurringExpenseReport() {
    return {
        type: "NAV_RECURRING_EXPENSE_REPORT"
    };
}

export function enableRecurringExpenseEditingAction(id) {
    return {
        data: id,
        type: "ENABLE_RECURRING_EXPENSE_EDITING"
    };
}

export function disableRecurringExpenseEditingAction(id) {
    return {
        data: id,
        type: "DISABLE_RECURRING_EXPENSE_EDITING"
    };
}

export function updateRecurringExpenseAction(data) {
    return {
        data: data,
        type: "UPDATE_RECURRING_EXPENSE"
    };
}

export function loginAction(data) {
    return {
        data: data,
        type: "LOGIN"
    };
}
export function loadDataAction() {
    return {
        type: "LOAD_DATA",
    };
};

export function loadStatsAction() {
    return {
        type: "LOAD_STATS",
    };
};

export function deleteExpenseAction(id) {
    return {
        type: "DELETE_EXPENSE",
        id: id
    };
};

export function createExpenseAction(data) {
    return {
        type: "CREATE_EXPENSE",
        data: data
    };
};

export function toggleForm() {
    return {
        type: "TOGGLE_FORM"
    };
};
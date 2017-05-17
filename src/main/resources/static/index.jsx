import React from 'react';
import ReactDOM from 'react-dom';
import expenseManagerApp from './reducers.js';
import ExpenseReport from './ExpenseReport.jsx';
import RecurringExpenseReport from './RecurringExpenseReport.jsx';
import TitleBar from './TitleBar.jsx';
import store from './expensesStore.js';

class App extends React.Component {
    constructor(props){
        super(props);
    }

    componentDidMount(){
        store.subscribe(() => {
            var state = store.getState();
            this.setState({
                display:"expenseReport"
            });
        });
    }

    render(){
        const display = store.getState().display;
        
        const setDisplay = (display) => {
            switch(display){
                case "expenseReport":
                    return <ExpenseReport/>;
                case "recurringExpenseReport":
                    return <RecurringExpenseReport/>;
                default:
                    return <p />;
            }
        };

        return(
            <div>
                <TitleBar />
                {setDisplay(display)}
            </div>
        );
    }
}

ReactDOM.render(
  <App />, document.getElementById('root')
);


import React from 'react';
import ReactDOM from 'react-dom';
import expenseManagerApp from './reducers.js';
import ExpenseReport from './ExpenseReport.jsx';
import RecurringExpenseReport from './RecurringExpenseReport.jsx';
import TitleBar from './TitleBar.jsx';
import store from './expensesStore.js';
import { fetchUserAction } from './actions.js';


class App extends React.Component {
    constructor(props){
        super(props);
    }

    componentDidMount(){
        store.subscribe(() => {
            this.setState({
                display:store.getState().display,
                isLoggedIn: store.getState().isLoggedIn
            });
        });
    }

    render(){
        const display = store.getState().display;

        const setDisplay = (display) => {
            if(store.getState().isLoggedIn){
                // store.dispatch(fetchUserAction());

                switch(display){
                    case "expenseReport":
                        return <ExpenseReport/>;
                    case "recurringExpenseReport":
                        return <RecurringExpenseReport/>;
                    default:
                        return <p/>;
                }
            }
            else{
                return <div/>
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


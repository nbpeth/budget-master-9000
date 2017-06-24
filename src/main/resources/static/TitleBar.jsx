import React from 'react';
import store from './expensesStore.js';
import LoginPage from './LoginPage.jsx';

import { navExpenseReport, navRecurringExpenseReport } from './actions.js';

class TitleBar extends React.Component{
    render(){
        const handleClick = (display) => {  
            switch(display){
                case "expense":
                    store.dispatch(navExpenseReport());
                    break;
                case "recurring":
                    store.dispatch(navRecurringExpenseReport());
                    break;
                default:
                    break;
            }
        };

        return(
            <div>
                <div className="container-fluid bg bg-gradient">
                    <h1>
                        Budget Master 9000
                    </h1>
                    
                    <LoginPage />
                    
                    <p/>
                    <nav className="navbar navbar-default navbar-fixed-top bg-active">
                        <div className="container-fluid">
                            <button className="btn-nooutline" type="button" onClick={ () => { handleClick("expense") }}>Expense Report</button>
                            <button className="btn-nooutline" type="button" onClick={ () => { handleClick("recurring") }}>Recurring Expenses</button>
                        </div>
                    </nav>

                </div>
            </div>
        );
    };
};

export default TitleBar;
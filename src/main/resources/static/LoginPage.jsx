import React from 'react';
import ReactDOM from 'react-dom';
import store from './expensesStore.js';
import { loginAction } from './actions.js';


class LoginPage extends React.Component {
    constructor(props){
        super(props);
    }

    componentDidMount(){
        store.subscribe(() => {
            this.setState({
                isLoggedIn: store.getState().isLoggedIn
            });
        });
    }

    render(){ 
        const isLoggedIn = store.getState().isLoggedIn;      
         
         const submitLogin = (loginInfo) => {
            store.dispatch(loginAction(loginInfo));        
         };
         
         const getLoginInfo = () => {
            return {
                "username" : document.getElementById("username").value,
                "password" : document.getElementById("password").value
            };
         };

        return(
            <div>
                {isLoggedIn ? "HEY" : 
                    <form className="form-inline">
                        <input type="text" className="form-control mb-2 mr-sm-2 mb-sm-0" id="username" placeholder="User Name" />
                        <input type="password" className="form-control mb-2 mr-sm-2 mb-sm-0" id="password" placeholder="Password" />
                        <button type="button" className="btn btn-primary" onClick={ () => submitLogin(getLoginInfo())}>Login</button>
                    </form>
                }
            </div>
        );
    }
}

export default LoginPage;
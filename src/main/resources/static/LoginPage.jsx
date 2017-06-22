import React from 'react';
import ReactDOM from 'react-dom';
import store from './expensesStore.js';
import { loginAction, logoutAction } from './actions.js';


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

         const submitLogout = () => {
            store.dispatch(logoutAction());
         };
         
         const getLoginInfo = () => {
            return {
                "username" : document.getElementById("username").value,
                "password" : document.getElementById("password").value
            };
         };

         const submitOnEnter = (e) => {
            if(e.keyCode == 13){
                submitLogin(getLoginInfo());
            }
         };

        return(
            <div>
                { isLoggedIn ? <a href ='#' onClick={ () => submitLogout()}>Logout</a> : 
                    <form className="form-inline">
                        <input type="text" className="form-control mb-2 mr-sm-2 mb-sm-0" id="username" placeholder="User Name" onKeyDown={ (e) => { submitOnEnter(e) } }/>
                        <input type="password" className="form-control mb-2 mr-sm-2 mb-sm-0" id="password" placeholder="Password" onKeyDown={ (e) => { submitOnEnter(e) } }/>
                        <button type="button" className="btn btn-primary" onClick={ () => submitLogin(getLoginInfo())}>Login</ button>
                    </form>
                }
            </div>
        );
    }
}

export default LoginPage;
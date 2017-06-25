import React from 'react';
import ReactDOM from 'react-dom';
import store from './expensesStore.js';
import { loginAction, logoutAction, fetchUserAction } from './actions.js';


class LoginPage extends React.Component {
    constructor(props){
        super(props);
    }

    componentDidMount(){
        store.subscribe(() => {
            this.setState({
                isLoggedIn: store.getState().isLoggedIn,
                user: store.getState().user
            });
        });
        store.dispatch(fetchUserAction());   

        
    }

    render(){ 
        const getUsername = () => {
            var user;

            if(store.getState().isLoggedIn){
                user = store.getState().user.username;
            } 

            return user;
        };
           
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

         const getGreeting = () => {
            return "Hello " + getUsername() + "! ";
         };

        return(
            <div>
                { store.getState().isLoggedIn ?  
                    <div>
                        {getGreeting()} <p/> <a href ='#' onClick={ () => submitLogout()}>Logout</a> 
                    </div> : 
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
import React, {Component} from 'react';
import NavBar from "./modules/views/NavBar/NavBar";
import UserBar from "./modules/views/UserBar/UserBar";
import AuthTokenService from "./services/auth/AuthTokenService";
import "./App.css";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn: AuthTokenService.loggedIn(),
        }
    }

    render() {
        const {isLoggedIn} = this.state;
        return <div className="background">
            <NavBar/>
            <div className="flex-container">
            {
                isLoggedIn ? <UserBar/> : false
            }
            {
                this.props.children
            }
            </div>
        </div>;
    }
}

export default App;

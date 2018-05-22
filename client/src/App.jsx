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
            query: null
        }
    }

    handleQuery(query) {
        this.setState(
            {
                query: query
            }
        )
    }

    render() {
        const {isLoggedIn} = this.state;
        return <div className="background">
            <NavBar onQuery={this.handleQuery.bind(this)}/>
            <div className="flex-container">
            {
                isLoggedIn ? <UserBar/> : false
            }
            {
                React.Children.map(this.props.children, child => React.cloneElement(child, {query: this.state.query}))
            }
            </div>
        </div>;
    }
}

export default App;

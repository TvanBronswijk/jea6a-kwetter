import React, { Component } from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import "./Router.css";
import Home from "./modules/views/Home/Home";
import Profile from "./modules/views/Profile/Profile";
import Register from "./modules/views/Register/Register";
import EditProfile from "./modules/views/Profile/EditProfile";

class Router extends Component {
    render() {
        return (
            <BrowserRouter>
                <div className="page-container">
                    <Route exact path='/'
                           component={(props) => <Home query={this.props.query} {...props} />} />

                    <Route exact path='/me/edit'
                           component={({ props }) => <EditProfile {...props} />} />

                    <Route exact path='/profile/:name'
                           component={({ match, props }) => <Profile username={match.params.name} {...props} />} />

                    <Route exact path='/register'
                           component={(props) => <Register {...props} />} />
                </div>
            </BrowserRouter>
        );
    }
}

export default Router;
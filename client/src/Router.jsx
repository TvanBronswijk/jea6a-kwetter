import React, { Component } from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import Home from "./modules/views/Home/Home";
import Profile from "./modules/views/Profile/Profile";
import "./Router.css";

class Router extends Component {
    render() {
        return (
            <BrowserRouter>
                <div className="page-container">
                    <Route exact path='/'
                           component={(props) => <Home {...props} />} />
                    <Route exact path='/profile/:name'
                           component={({ match, props }) => <Profile username={match.params.name} {...props} />} />
                </div>
            </BrowserRouter>
        );
    }
}

export default Router;
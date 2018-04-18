import React, {Component} from 'react';
import {Input, Menu} from "semantic-ui-react";
import NavBarLogin from "./NavBarLogin";
import NavBarLogout from "./NavBarLogout";
import AuthTokenService from "../../../services/auth/AuthTokenService";

class NavBar extends Component {
    static Login = NavBarLogin;
    static Logout = NavBarLogout;

    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn: AuthTokenService.loggedIn(),
        };
    }

    render() {
        const {isLoggedIn} = this.state;
        return <Menu borderless stackable inverted>
            <Menu.Item as={'a'} href={"/"} header>Kwetter</Menu.Item>
            <Menu.Item>
                <Input icon='search'/>
            </Menu.Item>
            { isLoggedIn ? <NavBar.Logout/> : <NavBar.Login/>}
        </Menu>;
    }
}

export default NavBar;
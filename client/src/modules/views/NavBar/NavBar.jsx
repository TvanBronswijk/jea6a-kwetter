import React, {Component} from 'react';
import {Icon, Input, Menu} from "semantic-ui-react";
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

    onChange(e) {
        const {onQuery} = this.props;
        if(onQuery){
            onQuery(e.target.value);
        }
    }

    render() {
        const {isLoggedIn} = this.state;

        return <Menu borderless stackable inverted>
            <Menu.Item as='a' href="/" header>Kwetter</Menu.Item>
            <Menu.Item>
                <Input icon='search' onChange={this.onChange.bind(this)} placeholder="Search..."/>
            </Menu.Item>
            <Menu.Menu>
                <Menu.Item as='a' href='/'><Icon name='home'/>Home</Menu.Item>
                {isLoggedIn
                    ? <Menu.Item as='a' href={'/profile/' + AuthTokenService.getProfile().username}>
                        <Icon name='user circle'/>My Profile
                    </Menu.Item>
                    : false
                }
            </Menu.Menu>
            {isLoggedIn ? <NavBar.Logout/> : <NavBar.Login/>}
        </Menu>;
    }
}

export default NavBar;
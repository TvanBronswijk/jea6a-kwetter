import React, {Component} from 'react';
import {Menu} from "semantic-ui-react";
import AuthTokenService from "../../../services/auth/AuthTokenService";

class NavBarLogin extends Component {

    handleItemClick() {
        AuthTokenService.logout();
        window.location.reload();
    }

    render() {
        return <Menu.Menu position='right'>
            <Menu.Item name='Logout' onClick={this.handleItemClick.bind(this)}/>
        </Menu.Menu>;
    }
}

export default NavBarLogin;
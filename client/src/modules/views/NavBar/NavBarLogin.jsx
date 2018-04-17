import React, {Component} from 'react';
import {Button, Input, Menu} from "semantic-ui-react";
import AuthTokenService from "../../../services/auth/AuthTokenService";

class NavBarLogin extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
        };
    }

    handleChange(e) {
        this.setState(
            {
                [e.target.name]: e.target.value
            }
        )
    }

    async handleItemClick() {
        const { username, password } = this.state;
        await AuthTokenService.login(username, password);
        window.location.reload();
    }

    render() {
        return <Menu.Menu position='right'>
            <Menu.Item>
                <Input icon='user' onChange={this.handleChange.bind(this)} placeholder='Username' name='username'/>
            </Menu.Item>
            <Menu.Item>
                <Input icon='lock' onChange={this.handleChange.bind(this)} placeholder='Password'
                       name='password' type='password'/>
            </Menu.Item>
            <Menu.Item name='Login' as={Button} onClick={this.handleItemClick.bind(this)}/>
        </Menu.Menu>;
    }
}

export default NavBarLogin;
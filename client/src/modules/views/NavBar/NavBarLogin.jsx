import React, {Component} from 'react';
import {Button, Form, Menu} from "semantic-ui-react";
import AuthTokenService from "../../../services/auth/AuthTokenService";
import './NavBar.css';

class NavBarLogin extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            err: null,
        };
    }

    handleChange(e) {
        this.setState(
            {
                err: null,
                [e.target.name]: e.target.value
            }
        )
    }

    async handleItemClick() {
        const {username, password} = this.state;
        try {
            await AuthTokenService.login(username, password);
            window.location.reload();
        } catch (e) {
            this.setState({err: e});
        }
    }

    render() {
        const {err} = this.state;

        return <Menu.Menu position='right'>
            <Menu.Item as='a' href='/register'>
                <h5>Not on Kwetter? Sign up!</h5>
            </Menu.Item>
            <Menu.Item className='navbar-login-item'>
                <Form size='tiny'>
                    <Form.Group className='navbar-login-form'>
                        <Form.Input className='navbar-login-field' icon='user' onChange={this.handleChange.bind(this)}
                                    placeholder='Username' name='username' type='text' required error={err!=null}/>
                        <Form.Input className='navbar-login-field' icon='lock' onChange={this.handleChange.bind(this)}
                                    placeholder='Password' name='password' type='password' required error={err!=null}/>
                        <Menu.Item name='Login' as={Button} onClick={this.handleItemClick.bind(this)}/>
                    </Form.Group>
                </Form>
            </Menu.Item>
        </Menu.Menu>;
    }
}

export default NavBarLogin;
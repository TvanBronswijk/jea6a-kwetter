import React, {Component} from 'react';
import {Card, Container, Divider, Form, Message} from "semantic-ui-react";
import AuthTokenService from "../../../services/auth/AuthTokenService";
import {Redirect} from "react-router";

class Register extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn: AuthTokenService.loggedIn(),
            username: null,
            password: null,
            email: null,
            redirect: false,
            err: null,
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
        const {username, password, email} = this.state;
        let body = JSON.stringify({username, password, email});
        try {
            await AuthTokenService.fetch(`/api/auth/register`, {
                method: 'POST',
                body: body,
            }).then(result => {
                return Promise.resolve(result);
            });
            await AuthTokenService.login(username, password);
            this.setState({redirect: true});
            window.location.reload();
        } catch (e) {
            this.setState({err: e});
        }
    }

    render() {
        const {isLoggedIn, redirect, err} = this.state;
        if(redirect){
            return <Redirect to="/"/>
        }
        return isLoggedIn ? <Container>
                <Message header="Error" content="You are already registered." size='huge' floating error/>
            </Container>
            : <Card centered raised fluid>
                <Card.Content>
                    <Card.Header>Register</Card.Header>
                    <Divider/>
                    <Form size='large'>
                        <Form.Input icon='user' name='username' placeholder='Username' maxLength={16}
                                    onChange={this.handleChange.bind(this)} error={err!=null} type='text'/>
                        <Form.Input icon='lock' name='password' placeholder='Password' maxLength={16}
                                    onChange={this.handleChange.bind(this)} error={err!=null} type='password'/>
                        <Form.Input icon='mail' name='email' placeholder='E-mail' maxLength={64}
                                    onChange={this.handleChange.bind(this)} error={err!=null} typ='email'/>
                        <Form.Button type='submit' onClick={this.handleItemClick.bind(this)}>Submit</Form.Button>
                    </Form>
                </Card.Content>
            </Card>;
    }
}

export default Register;
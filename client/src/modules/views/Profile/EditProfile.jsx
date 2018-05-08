import React, {Component} from 'react';
import AuthTokenService from "../../../services/auth/AuthTokenService";
import PrefabLoader from "../../components/Loader/PrefabLoader";
import {Card, Container, Divider, Form} from "semantic-ui-react";
import {Redirect} from "react-router";
import JSOG from 'jsog';

class EditProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ready: false,
            user: null,
        }
    }

    componentDidMount() {
        this.fetchUser();
    }

    handleChange(e) {
        let {user} = this.state;
        user.userDetails[e.target.name] = e.target.value;
        this.setState(
            {
                user: user
            }
        )
    }

    fetchUser() {
        try {
        const username = AuthTokenService.getProfile().username;
        AuthTokenService.fetch(`/api/users/name/${username}`)
            .then((user) => {
                this.setState({user: user, ready: true});
                return Promise.resolve(user);
            });
        } catch (e) {
            console.log(e);
            this.setState({err: e});
        }
    }

    async updateUser() {
        const {user} = this.state;
        let body = JSOG.stringify(user);
        console.log(body);
        try {
            await AuthTokenService.fetch(`/api/users`, {
                method: 'PATCH',
                body: body,
            }).then(result => {
                return Promise.resolve(result);
            });
            window.location.reload();
        } catch (e) {
            console.log(e);
            this.setState({err: e});
        }
    }

    //RENDERING
    render() {
        const { user, ready, err } = this.state;
        if(err){
            return <Redirect to='/'/>
        }
        return <Container>
        {ready ? <Card centered raised fluid>
                <Card.Content>
                    <Card.Header>Edit</Card.Header>
                    <Divider/>
                    <Form size='large' onSubmit={this.updateUser.bind(this)}>
                        <Form.Input name='imageURL' placeholder='ImageURL' value={user.userDetails.imageURL ? user.userDetails.imageURL : ''}
                                    onChange={this.handleChange.bind(this)} type='url'/>
                        <Form.Input name='name' placeholder='Name' value={user.userDetails.name ? user.userDetails.name : ''}
                                    onChange={this.handleChange.bind(this)} type='text'/>
                        <Form.Input name='location' placeholder='Location' value={user.userDetails.location ? user.userDetails.location : ''}
                                    onChange={this.handleChange.bind(this)} type='text'/>
                        <Form.TextArea name='bio' placeholder='Bio' value={user.userDetails.bio ? user.userDetails.bio : ''}
                                    onChange={this.handleChange.bind(this)} type='text'/>
                        <Form.Input name='web' placeholder='Website' value={user.userDetails.web ? user.userDetails.web : ''}
                                    onChange={this.handleChange.bind(this)} type='url'/>
                        <Form.Button primary content='Save' type='submit'/>
                    </Form>
                </Card.Content>
            </Card>
            : <PrefabLoader/>
        }</Container>;
    }
}

export default EditProfile;
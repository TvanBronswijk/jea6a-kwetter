import React, {Component} from 'react';
import User from "../../components/User/User";
import {Container} from "semantic-ui-react";
import AuthTokenService from "../../../services/auth/AuthTokenService";
import PrefabLoader from "../../components/Loader/PrefabLoader";

class Profile extends Component {
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

    fetchUser() {
        const username = this.props.username;
        AuthTokenService.fetch(`/api/users/name/${username}`)
            .then((user) => {
                this.setState({user: user, ready: true});
                return Promise.resolve(user);
            });
    }

    //RENDERING
    render() {
        const {ready} = this.state;
        return <Container>
            {ready ? this.renderUser() : <PrefabLoader/>}
        </Container>;
    }

    renderUser() {
        const {user} = this.state;
        return <User data={user}/>;
    }
}

export default Profile;
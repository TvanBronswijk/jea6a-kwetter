import React, {Component} from 'react';
import User from "../../components/User/User";
import {Container, Dimmer, Loader} from "semantic-ui-react";

class Profile extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ready: false,
            user: null,
        }
    }

    componentDidMount() {
        this.getUser();
    }

    getUser() {
        const username = this.props.username;
        fetch(`/api/users/byname/${username}`)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                }
                throw new Error("Cannot reach API.")
            })
            .then((userData) => {
                this.setState({user: userData, ready: true});
            });
    }

    renderUser() {
        const {user} = this.state;
        return <User data={user}/>;
    }

    render() {
        const {ready} = this.state;
        return <Container>
            {ready ? this.renderUser() : <Dimmer page active><Loader>Loading User</Loader></Dimmer>}
        </Container>;
    }
}

export default Profile;
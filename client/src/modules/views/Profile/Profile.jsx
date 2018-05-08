import React, {Component} from 'react';
import User from "../../components/User/User";
import AuthTokenService from "../../../services/auth/AuthTokenService";
import PrefabLoader from "../../components/Loader/PrefabLoader";
import {Container} from "semantic-ui-react";
import MyProfile from "./MyProfile";
import {Redirect} from "react-router";

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

    async onFollow(data) {
        const {id} = data;
        try {
            await AuthTokenService.fetch(`/api/users/${id}/follow`, {
                method: 'PATCH'
            }).then(result => {
                return Promise.resolve(result);
            });
            window.location.reload();
        } catch (e) {
            console.log(e);
            this.setState({err: e});
        }
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
        const {ready, user} = this.state;
        let username = false;
        try{
            username = AuthTokenService.getProfile().username;
        }catch(e){
            console.log("Not logged in.");
        }

        if(ready && user.username === username){
            return <MyProfile username={user.username}/>
        }
        return <Container>
            {ready
                ? <User data={user} loggedInUser={{username: username}} onFollow={this.onFollow.bind(this)} />
                : <PrefabLoader/>}
        </Container>;
    }
}

export default Profile;
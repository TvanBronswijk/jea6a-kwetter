import React, {Component} from 'react';
import AuthTokenService from "../../../services/auth/AuthTokenService";
import User from "../../components/User/User";
import PrefabLoader from "../../components/Loader/PrefabLoader";
import './UserBar.css';

class UserBar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            user: null,
            ready: false,
        };
    }

    componentDidMount() {
        this.fetchUser();
    }

    fetchUser() {
        const profile = AuthTokenService.getProfile();
        if(!profile){
            return;
        }
        const username = profile.username;
        AuthTokenService.fetch(`/api/users/name/${username}`)
            .then((user) => {
                this.setState({user: user, ready: true});
                return Promise.resolve(user);
            });
    }

    render() {
        const {ready, user} = this.state;
        return <div className="userbar-container">
            { ready ? <User data={user} followed={true}/> : <PrefabLoader/> }
        </div>;
    }
}

export default UserBar;
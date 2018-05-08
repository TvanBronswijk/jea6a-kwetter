import React, {Component} from 'react';
import User from "../../components/User/User";
import AuthTokenService from "../../../services/auth/AuthTokenService";
import PrefabLoader from "../../components/Loader/PrefabLoader";
import {Card, Container, Header, Item} from "semantic-ui-react";
import Post from "../../components/Post/Post";
import Follow from "../../components/User/Follow";

class MyProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ready: false,
            followers: [],
            following: [],
            posts: [],
        }
    }

    componentDidMount() {
        this.fetchFollowers();
        this.fetchFollowing();
        this.fetchPosts();
        this.setState({ready: true});
    }

    async fetchFollowers(){
        const username = this.props.username;
        await AuthTokenService.fetch(`/api/users/name/${username}/followers`)
            .then((followers) => {
                this.setState({followers: followers});
                return Promise.resolve(followers);
            });
    }

    async fetchFollowing(){
        const username = this.props.username;
        await AuthTokenService.fetch(`/api/users/name/${username}/following`)
            .then((following) => {
                this.setState({following: following});
                return Promise.resolve(following);
            });
    }


    async fetchPosts(){
        const username = this.props.username;
        await AuthTokenService.fetch(`/api/users/name/${username}/posts`)
            .then((posts) => {
                posts.reverse();
                this.setState({posts: posts});
                return Promise.resolve(posts);
            });
    }

    //RENDERING
    render() {
        const {ready, followers, following, posts} = this.state;
        return <Container>
            {ready
                ? <div>
                    <Card.Group>
                    <Card>
                        <Card.Content>
                            <Card.Header>Followers</Card.Header>
                            {
                                followers.map((f) => <Follow data={f}/>)
                            }
                        </Card.Content>
                    </Card>
                    <Card>
                        <Card.Content>
                            <Card.Header>Following</Card.Header>
                            {
                                following.map((f) => <Follow data={f}/>)
                            }
                        </Card.Content>
                    </Card>
                </Card.Group>
                    <Post.Group>
                        <Header as='h3' dividing>Kwets</Header>
                        {
                            posts ? posts.map((post, i) => <Post key={i} data={post}/>) : false
                        }
                    </Post.Group>
                </div>
                : <PrefabLoader/>
            }
        </Container>;
    }
}

export default MyProfile;
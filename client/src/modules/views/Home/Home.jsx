import React, {Component} from 'react';
import {Container, Header} from "semantic-ui-react";
import Post from "../../components/Post/Post";
import AuthTokenService from "../../../services/auth/AuthTokenService";
import PrefabLoader from "../../components/Loader/PrefabLoader";

class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ready: false,
            data: null,
        }
    }

    componentDidMount() {
        this.fetchPosts();
    }

    fetchPosts(){
        AuthTokenService.fetch('/api/posts')
            .then((posts) => {
                this.setState({data: posts, ready: true});
                return Promise.resolve(posts);
            });
    }

    postPost(){
        let body = {
          content: null,

        };
        AuthTokenService.fetch(`/api/posts`, {
            method: 'POST',
            body: body,
        }).then(result => {
            return Promise.resolve(result);
        });
    }

    handleSubmit() {

        window.location.reload();
    }

    //RENDERING
    render() {
        const {ready} = this.state;

        return <Container text>
            {ready ? this.renderPosts() : <PrefabLoader/>}
        </Container>;
    }

    renderPosts() {
        const {data} = this.state;
        return <Post.Group>
            <Header as='h3' dividing>Kwets</Header>
            {
                data ? data.map((post, i) => <Post key={i} data={post}/>) : false
            }
            {
                AuthTokenService.loggedIn() ? <Post.Reply /> : false
            }
        </Post.Group>
    }
}

export default Home;
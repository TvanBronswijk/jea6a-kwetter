import React, {Component} from 'react';
import {Container, Dimmer, Header, Loader} from "semantic-ui-react";
import Post from "../../components/Post/Post";

class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ready: false,
            data: null,
        }
    }

    componentDidMount() {
        this.getPosts();
    }

    getPosts() {
        fetch('/api/posts')
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                }
                throw new Error("Cannot reach API.")
            })
            .then((postData) => {
                this.setState({data: postData, ready: true});
            });
    }

    renderPosts() {
        const {data} = this.state;
        return <Post.Group>
            <Header as='h3' dividing>Kwets</Header>
            {
                data ? data.map((post, i) => this.renderPost(i, post)) : false
            }
        </Post.Group>
    }

    renderPost(key, post) {
        return <Post key={key} data={post}/>;
    }

    render() {
        const {ready} = this.state;

        return <Container text>
            {ready ? this.renderPosts() : <Dimmer page active><Loader>Loading Kwets</Loader></Dimmer>}
        </Container>;
    }
}

export default Home;
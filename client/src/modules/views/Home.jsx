import React, {Component} from 'react';
import {Header, Loader} from "semantic-ui-react";
import Post from "../components/Post/Post";

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
                console.log(postData);
                this.setState({ data: postData, ready: true });
            });
    }

    renderPosts(){
        const { data } = this.state;
        return data ? data.map((post, i) => this.renderPost(post)) : false;
    }

    renderPost(post){
        return <Post data={post} />;
    }

    render() {
        const { ready } = this.state;

        return ready ? <Post.Group>
            <Header as='h3' dividing>Kwets</Header>
                {
                    this.renderPosts()
                }
        </Post.Group>
            : <Loader />;
    }
}

export default Home;
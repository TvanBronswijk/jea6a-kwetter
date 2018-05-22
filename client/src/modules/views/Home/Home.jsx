import React, {Component} from 'react';
import {Container, Header} from "semantic-ui-react";
import Post from "../../components/Post/Post";
import AuthTokenService from "../../../services/auth/AuthTokenService";
import PrefabLoader from "../../components/Loader/PrefabLoader";

class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn: AuthTokenService.loggedIn(),
            ready: false,
            data: null,
            reply: '',
            err: null,
        }
    }

    handleChange(e) {
        this.setState(
            {
                [e.target.name]: e.target.value
            }
        )
    }

    componentDidMount() {
        this.fetchPosts();
    }

    fetchPosts() {
        console.log(JSON.stringify(this.props));
        const { query } = this.props;
        if(query) {
            console.log("Query");
            AuthTokenService.fetch( `/api/posts/search?query=${query}`)
                .then((posts) => {
                    posts.reverse();
                    this.setState({data: posts, ready: true});
                    return Promise.resolve(posts);
                });
        }else {
            console.log("All");
            AuthTokenService.fetch('/api/posts')
                .then((posts) => {
                    posts.reverse();
                    this.setState({data: posts, ready: true});
                    return Promise.resolve(posts);
                });
        }
    }


    async handleSubmit() {
        const {reply} = this.state;
        let body = JSON.stringify({content: reply});
        try {
            await AuthTokenService.fetch(`/api/posts`, {
                method: 'POST',
                body: body,
            }).then(result => {
                return Promise.resolve(result);
            });
            this.setState({reply: '', ready: false});
            this.fetchPosts();
        } catch (e) {
            console.log(e);
            this.setState({err: e});
        }
    }

    async onLike(data) {
        const {id} = data;
        try {
            await AuthTokenService.fetch(`/api/posts/${id}/like`, {
                method: 'PATCH'
            }).then(result => {
                return Promise.resolve(result);
            });
            this.setState({ready: false});
            this.fetchPosts();
        } catch (e) {
            console.log(e);
            this.setState({err: e, ready: false});
            this.fetchPosts();
        }
    }

    //RENDERING
    render() {
        const {ready} = this.state;

        return <Container>
            {ready ? this.renderPosts() : <PrefabLoader/>}
        </Container>;
    }

    renderPosts() {
        const {isLoggedIn, data} = this.state;
        return <Post.Group>
            <Header as='h3' dividing>Kwets</Header>
            {
                isLoggedIn ? <div>
                        <Post.Reply name='reply' onChange={this.handleChange.bind(this)} onClick={this.handleSubmit.bind(this)}/>
                        <br/>
                        <br/>
                    </div>
                    : false
            }
            {
                data ? data.map((post, i) => <Post key={i} data={post} likable={isLoggedIn} onLike={this.onLike.bind(this)}/>) : false
            }
        </Post.Group>
    }
}

export default Home;
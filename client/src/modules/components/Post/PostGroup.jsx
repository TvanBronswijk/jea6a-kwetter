import React, {Component} from 'react';
import {Comment} from "semantic-ui-react";
import Post from "./Post";

class PostGroup extends Component {

    render() {
        return <Comment.Group minimal size="large">
            {
                this.props.children
            }
        </Comment.Group>;
    }
}

export default PostGroup;
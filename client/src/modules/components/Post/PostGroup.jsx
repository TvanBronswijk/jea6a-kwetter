import React, {Component} from 'react';
import {Comment} from "semantic-ui-react";

class Post extends Component {

    render() {
        return <Comment.Group minimal>
            {
                this.props.children
            }
        </Comment.Group>;
    }
}

export default Post;
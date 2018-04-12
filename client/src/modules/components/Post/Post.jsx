import React, {Component} from 'react';
import {Comment} from "semantic-ui-react";
import PostGroup from "./PostGroup";

class Post extends Component {
    static Group = PostGroup;

    render() {
        const { data } = this.props;

        return <Comment>
            <Comment.Content>
                <Comment.Author as='a'>{data.user.username}</Comment.Author>
                <Comment.Metadata>
                    <div>{data.timestamp}</div>
                </Comment.Metadata>
                <Comment.Text>{data.content}</Comment.Text>
                <Comment.Actions>
                    <Comment.Action>Like</Comment.Action>
                </Comment.Actions>
            </Comment.Content>
        </Comment>;
    }
}

export default Post;
import React, {Component} from 'react';
import {Comment} from "semantic-ui-react";
import PostGroup from "./PostGroup";
import Moment from "react-moment";

class Post extends Component {
    static Group = PostGroup;

    render() {
        const { data } = this.props;

        return <Comment>
            <Comment.Avatar src={data.user.userDetails.imageURL} />
            <Comment.Content>
                <Comment.Author as='a' href={"/profile/"+data.user.username}>{data.user.username}</Comment.Author>
                <Comment.Metadata>
                    <Moment format="dddd MMMM DD" unix>{data.timestamp/1000}</Moment>
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
import React, {Component} from 'react';
import {Comment} from "semantic-ui-react";
import PostGroup from "./PostGroup";
import Moment from "react-moment";
import PostReply from "./PostReply";
import "./Post.css";

class Post extends Component {
    static Group = PostGroup;
    static Reply = PostReply;

    render() {
        const { data } = this.props;

        return <Comment>
            <Comment.Avatar src={data.user.userDetails ? data.user.userDetails.imageURL : false} />
            <Comment.Content>
                <Comment.Author as='a' href={"/profile/" + data.user.username}>{data.user.username}</Comment.Author>
                <Comment.Metadata>
                    <Moment format="dddd, DD MMM YYYY HH:mm" unix>{data.timestamp/1000}</Moment>
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
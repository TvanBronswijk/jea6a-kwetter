import React, {Component} from 'react';
import {Button, Comment} from "semantic-ui-react";
import PostGroup from "./PostGroup";
import Moment from "react-moment";
import PostReply from "./PostReply";
import "./Post.css";

class Post extends Component {
    static Group = PostGroup;
    static Reply = PostReply;

    onLike() {
        const {data, onLike} = this.props;
        if(onLike) {
            onLike(data);
        }
    }

    onDelete() {
        const {data, onDelete} = this.props;
        if(onDelete) {
            onDelete(data);
        }
    }

    render() {
        const { data, likable, deletable } = this.props;
        return <Comment>
            <Comment.Avatar src={data.user.userDetails ? data.user.userDetails.imageURL : false} />
            <Comment.Content>
                <Comment.Author as='a' href={"/profile/" + data.user.username}>{data.user.username}</Comment.Author>
                <Comment.Metadata>
                    <Moment format="dddd, DD MMM YYYY HH:mm" unix>{data.timestamp/1000}</Moment>
                </Comment.Metadata>
                <Comment.Text>{data.content}</Comment.Text>
                <Comment.Metadata>
                    <span>Likes: {data.likeCount}</span>
                </Comment.Metadata>
                <Comment.Actions>
                    { likable ? <Comment.Action icon='like' basic compact size='tiny' as={Button} onClick={this.onLike.bind(this)} label='Like' /> : false }
                    { deletable ? <Comment.Action icon='trash' basic compact size='tiny' as={Button} onClick={this.onDelete.bind(this)} label='Delete' /> : false }
                </Comment.Actions>
            </Comment.Content>
        </Comment>;
    }
}

export default Post;
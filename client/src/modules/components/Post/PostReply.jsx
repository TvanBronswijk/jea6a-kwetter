import React, {Component} from 'react';
import {Button, Form} from "semantic-ui-react";
import "./Post.css";

class PostReply extends Component {

    render() {
        const {name, onClick, onChange, text} = this.props;
        return <Form reply>
            <Form.TextArea name={name} onChange={onChange} autoHeight placeholder="Tell us what you're thinking!" rows={1} maxLength={140} value={text} />
            <Button onClick={onClick} primary compact content='Post it!' labelPosition='left' icon='edit' floated='right' type="submit" />
        </Form>;
    }
}

export default PostReply;
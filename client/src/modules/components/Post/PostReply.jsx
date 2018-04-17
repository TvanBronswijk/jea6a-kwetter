import React, {Component} from 'react';
import {Button, Form} from "semantic-ui-react";

class PostReply extends Component {

    render() {
        return <Form reply>
            <Form.TextArea />
            <Button content='Post it!' labelPosition='left' icon='edit' primary type="submit" />
        </Form>;
    }
}

export default PostReply;
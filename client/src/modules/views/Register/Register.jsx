import React, {Component} from 'react';
import {Button, Form} from "semantic-ui-react";

class Register extends Component {

    render() {
        return <Form>
            <Form.TextArea autoHeight placeholder="Tell us what you're thinking!" rows={1} maxLength={140} value={text} />
            <Button primary compact content='Post it!' labelPosition='left' icon='edit' floated='right' type="submit" />
        </Form>;
    }
}

export default Register;
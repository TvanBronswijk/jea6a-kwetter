import React, {Component} from 'react';
import {Card, Image} from "semantic-ui-react";

class Follow extends Component {

    render() {
        const {data} = this.props;

        return <Card>
            <Card.Content>
                <Image floated='right' size='mini' src={data.userDetails.imageURL || "http://alarishealth.com/wp-content/uploads/2014/06/no-user.png"} />
                <Card.Header as='a' href={"/profile/" + data.username}>
                    @{data.username}
                </Card.Header>
            </Card.Content>
        </Card>
    }
}

export default Follow;
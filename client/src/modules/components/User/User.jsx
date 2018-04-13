import React, {Component} from 'react';
import {Button, Card, Image} from "semantic-ui-react";

class User extends Component {

    render() {
        const {data} = this.props;

        return <Card>
            <Image src={data.userDetails.imageURL}/>
            <Card.Content>
                <Card.Header>
                    {data.username}
                </Card.Header>
                <Card.Meta>
                    <span>{data.userDetails.name}</span>
                    <span>|</span>
                    <span>{data.userDetails.location}</span>
                </Card.Meta>
            </Card.Content>
            <Card.Content>
                <Card.Description>
                    {data.userDetails.bio}
                </Card.Description>
            </Card.Content>
            <Card.Content extra>
                <span>{data.userDetails.web}</span>
                <Button size={'mini'} floated={'right'} compact primary toggle>Follow</Button>
            </Card.Content>
        </Card>;
    }
}

export default User;
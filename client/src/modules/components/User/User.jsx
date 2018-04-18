import React, {Component} from 'react';
import {Button, Card, Icon, Image} from "semantic-ui-react";

class User extends Component {

    render() {
        const {data, followed} = this.props;

        return data.userDetails
            ? <Card>
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
                    <span><a href={data.userDetails.web}>Website</a></span>
                    <Button size={'mini'} floated={'right'} compact primary disabled={followed}>
                        {followed ? "Followed" : "Follow"}
                    </Button>
                </Card.Content>
            </Card>
            : <Card>
                <Card.Content>
                    <Card.Description>
                        <Icon name="warning circle"/>
                        This user has no profile
                    </Card.Description>
                </Card.Content>
            </Card>;
    }
}

export default User;
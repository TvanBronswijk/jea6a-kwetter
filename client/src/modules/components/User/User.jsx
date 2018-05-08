import React, {Component} from 'react';
import {Button, Card, Icon, Image} from "semantic-ui-react";

class User extends Component {

    onClick(){
        const {data, onFollow} = this.props;
        if(onFollow){
            onFollow(data);
        }
    }

    render() {
        const {data, followed, loggedInUser} = this.props;

        return data.userDetails
            ? <Card>
                <Image src={data.userDetails.imageURL || "http://alarishealth.com/wp-content/uploads/2014/06/no-user.png"}/>
                <Card.Content>
                    <Card.Header>
                        @{data.username}
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
                    {
                        data.username === loggedInUser.username
                            ? <Button size={'mini'} floated={'right'} compact primary href='/me/edit'>
                                Edit
                            </Button>
                            : <Button size={'mini'} floated={'right'} compact primary onClick={this.onClick.bind(this)}
                                      disabled={followed}>
                                {followed ? "Followed" : "Follow"}
                            </Button>
                    }
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
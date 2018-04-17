import React, {Component} from 'react';
import {Loader} from 'semantic-ui-react';

class PrefabLoader extends Component {
    render() {
        return <Loader active>
            <h5>Loading...</h5>
        </Loader>;
    }
}

export default PrefabLoader;
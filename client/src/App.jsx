import React, { Component } from 'react';
import Home from "./modules/views/Home";
import {Menu} from "semantic-ui-react";

class App extends Component {
  render() {
    return <div>
        <Menu inverted> </Menu>
        <Home />
    </div>;
  }
}

export default App;

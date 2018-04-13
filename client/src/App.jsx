import React, { Component } from 'react';
import NavBar from "./modules/views/NavBar/NavBar";

class App extends Component {
  render() {
    return <div>
        <NavBar/>
        {
            this.props.children
        }
    </div>;
  }
}

export default App;

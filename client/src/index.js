import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'semantic-ui-css/semantic.min.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import Router from "./Router";

ReactDOM.render(<App><Router/></App>, document.getElementById('root'));
registerServiceWorker();

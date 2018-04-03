import React, { Component } from 'react'
import './App.css'
import UsersList from './components/UsersList'

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">User Management Application</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <UsersList/>
      </div>
    );
  }
}

export default App;

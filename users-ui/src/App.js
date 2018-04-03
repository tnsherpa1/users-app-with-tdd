import React, { Component } from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import './App.css'
import Nav from './components/Nav'
import UserNewForm from './components/UserNewForm'
import UsersList from './components/UsersList'
class App extends Component {
  render() {
    return (
      <Router>
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">User Management Application</h1>
          <Nav/>
        </header>
        <Switch>
          <Route exact path='/' component={UserNewForm} />
          <Route exact path='/myusers' component={UsersList}/>
          <Route render={ ()=><p>404 Not found</p> }/>
        </Switch>
        <UsersList/>
      </div>
      </Router>
    );
  }
}

export default App;

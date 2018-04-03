import React, { Component } from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import './App.css'
import Nav from './components/Nav'
import UserNewForm from './components/UserNewForm'
import UsersList from './components/UsersList'
import axios from "axios";

class App extends Component {
  state = {
    users: []
  };

  async componentDidMount(){
    try {
      const response = await axios.get(`/users/`)
      this.setState({users: response.data})
    } catch(error) {
      console.log(error)
    }
  }

  createUser = async(user, index) => {
    try {
      const newUserResponse = await axios.post(`/users`, user)
      const updatedUserResponse = [...this.state.users]
      updatedUserResponse.push(newUserResponse.data)
      this.setState({users: updatedUserResponse})
    } catch(error) {
      console.log("error creating new user")
      console.log(error)
    }
  }

  render() {
    return (
      <Router>
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">User Management Application</h1>
          <Nav/>
        </header>
        <Switch>
          <Route exact path='/' render={()=><UserNewForm createUser={this.createUser}/>} />
          <Route exact path='/myusers' render={()=><UsersList users={this.state.users}/>} />
          <Route render={ ()=><p>404 Not found</p> }/>
        </Switch>
      </div>
      </Router>
    );
  }
}

export default App;

import React, {Component} from 'react'
import axios from 'axios'
import UserNewForm from './UserNewForm'
import User from './User'

class UsersList extends Component {
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
    const userComponents =  this.state.users.map((user, index) => {
      return <User key={index} username={user.userName} firstname={user.firstName} lastname={user.lastName}/>
    })
    return (
      <div>
        {userComponents}
        <hr/>
        <UserNewForm createUser={this.createUser}/>
      </div>
    )
  }
}

export default UsersList


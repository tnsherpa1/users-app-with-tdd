import React, {Component} from 'react'
import axios from 'axios'

class UsersList extends Component {
  state = {
    users: []
  };

  async componentDidMount(){
    try {
      const response = await axios.get("/users/")
      this.setState({users: response.data})
    } catch(e) {
      console.log(e)
    }
  }
  render() {
    const userName = this.state.users.map((user)=>{
      return <p>{user.username}</p>
    })
    return (
      <div>
        {userName}
      </div>
    )
  }
}

export default UsersList


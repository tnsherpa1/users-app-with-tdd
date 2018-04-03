import React, {Component} from 'react'
import axios from 'axios'
import UserNewForm from './UserNewForm'
import User from './User'

class UsersList extends Component {

  render() {
    const userComponents = this.props.users.map((user, index) => {
      return <User key={index} username={user.userName} firstname={user.firstName} lastname={user.lastName}/>
    })

    return (
      <div>
        {userComponents}
        <hr/>
      </div>
    )
  }
}

export default UsersList


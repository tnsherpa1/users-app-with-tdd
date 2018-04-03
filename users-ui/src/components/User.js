import React, {Component} from 'react'

class User extends Component {
  render() {
    return (
      <div>
        <ul>
          <li>{this.props.username}</li>
          <li>{this.props.firstname}</li>
          <li>{this.props.lastname}</li>
        </ul>
      </div>
    )
  }
}

export default User
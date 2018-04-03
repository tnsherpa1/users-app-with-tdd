import React, {Component} from 'react'

class User extends Component {
  render() {
    return (
        <ul className='users'>
          <li className='user-box'>
            <ul className="user-spaced">
            <li>@{this.props.username}</li>
            <li>{this.props.firstname +' '+this.props.lastname}</li>
            </ul>
          </li>
        </ul>
    )
  }
}

export default User
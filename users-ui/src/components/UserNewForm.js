import React, {Component} from 'react'

class userNewForm extends Component {
    render() {
        return (
            <div>
                <form>
                  <ul className="wrapper">
                    <li className="form-row">
                      <label htmlFor="title">Username</label>
                      <input name="username" type="text" placeholder="Username"/>
                    </li>
                    <li className="form-row">
                      <label htmlFor="firstname">First Name</label>
                      <input name="firstname" type="text" placeholder="First Name"/>
                    </li>
                    <li className="form-row">
                      <label htmlFor="lastname">Last Name</label>
                      <input name="lastname" type="text" placeholder="Last Name"/>
                    </li>
                    <li className="form-row btn-row">
                      <button type="submit">Create New User</button>
                    </li>
                  </ul>
                </form>
            </div>
        )
    }
}

export default userNewForm
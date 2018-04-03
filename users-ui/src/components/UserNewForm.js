import React, {Component} from 'react'

class userNewForm extends Component {
    state = {
      newUser: {}
    }
    handleSubmit = (e) => {
      e.preventDefault()
      this.props.createUser(this.state.newUser)
    }
    handleChange = (e) => {
      const attributeToChange = e.target.name
      const newValue = e.target.value
      const updatedNewUser = {...this.state.newUser}
      updatedNewUser[attributeToChange] = newValue
      this.setState({
        newUser: updatedNewUser
      })
    }
    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                  <ul className="wrapper">
                    <li className="form-row">
                      <label htmlFor="title">Username</label>
                      <input name="userName" type="text" placeholder="Username" onChange={this.handleChange}/>
                    </li>
                    <li className="form-row">
                      <label htmlFor="firstname">First Name</label>
                      <input name="firstName" type="text" placeholder="First Name" onChange={this.handleChange}/>
                    </li>
                    <li className="form-row">
                      <label htmlFor="lastname">Last Name</label>
                      <input name="lastName" type="text" placeholder="Last Name" onChange={this.handleChange}/>
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
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
                <form className='column' onSubmit={this.handleSubmit}>
                  <ul className="wrapper">
                    <li className="form-row">
                      <label className='header' htmlFor="title">Username</label>
                      <input autoComplete='off' name="userName" type="text" placeholder="Username" onChange={this.handleChange}/>
                    </li>
                    <li className="form-row">
                      <label className='header' htmlFor="firstname">First Name</label>
                      <input autoComplete='off' name="firstName" type="text" placeholder="First Name" onChange={this.handleChange}/>
                    </li>
                    <li className="form-row">
                      <label className='header' htmlFor="lastname">Last Name</label>
                      <input autoComplete='off' name="lastName" type="text" placeholder="Last Name" onChange={this.handleChange}/>
                    </li>
                    <li className="form-row btn-row">
                      <button className='button' type="submit">Create New User</button>
                    </li>
                  </ul>
                </form>
            </div>
        )
    }
}

export default userNewForm
import React from 'react'
import {NavLink} from 'react-router-dom'

const Nav = () => {
  return (
    <ul>
      <li>
        <NavLink exact activeClassName='/active' to='/'>Home</NavLink>
        <NavLink activeClassName='active' to='/myusers'>Users</NavLink>
      </li>
    </ul>
  )
}

export default Nav

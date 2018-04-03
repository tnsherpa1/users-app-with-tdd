import React from 'react'
import {NavLink} from 'react-router-dom'

const Nav = () => {
  return (
    <ul className='nav'>
      <li>
        <NavLink exact activeClassName='active' to='/'>Create</NavLink>
        <NavLink activeClassName='active' to='/myusers'>View all Users</NavLink>
        <NavLink activeClassName='active' to='/updateusers'>Update</NavLink>
        <NavLink activeClassName='active' to='/deleteusers'>Delete</NavLink>
      </li>
    </ul>
  )
}

export default Nav

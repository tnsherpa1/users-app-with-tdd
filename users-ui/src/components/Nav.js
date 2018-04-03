import React from 'react'
import {NavLink} from 'react-router-dom'

const Nav = () => {
  return (
    <ul className='nav'>
      <li>
        <NavLink id='new-user-link' exact activeClassName='active' to='/'>Create</NavLink>
        <NavLink id='view-users' activeClassName='active' to='/myusers'>View all Users</NavLink>
        <NavLink activeClassName='active' to='/updateusers'>Update</NavLink>
        <NavLink activeClassName='active' to='/deleteusers'>Delete</NavLink>
      </li>
    </ul>
  )
}

export default Nav

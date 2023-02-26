import React, { useContext, useState } from 'react';
import { Link, NavLink } from 'react-router-dom';
import '../App.css';
import AuthContext from '../context/AuthContext';

function Header() {
	const context = useContext(AuthContext);

	const { isLoggedIn, logout } = context;
	const logoutHandler = () => {
		// console.log("logging out from header");
		logout();
	};
	return (
		<>
			<ul className='header'>
				<div>
					<li>
						<Link to='/posts'>Home</Link>
					</li>
				</div>
				<div className='wrapper-list'>
					{!isLoggedIn && (
						<>
							<li>
								<Link as={NavLink} to='/login'>
									Login
								</Link>
							</li>
							<li>
								<Link as={NavLink} to='/register'>
									Register
								</Link>
							</li>
						</>
					)}

					{isLoggedIn && (
						<>
							<li>
								<button onClick={() => logoutHandler()}>Log Out</button>
							</li>
							<li>
								<Link as={NavLink} to='/new-post'>
									Add Post
								</Link>
							</li>
						</>
					)}
				</div>
			</ul>
		</>
	);
}

export default Header;

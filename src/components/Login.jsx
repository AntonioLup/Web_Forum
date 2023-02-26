import React, { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../context/Api';
import AuthContext from '../context/AuthContext';
import LoginRegister from './forms/LoginRegister';

const Login = props => {
	const [isLoginSuccess, setIsLoginSuccess] = useState(false);
	const authCtx = useContext(AuthContext);
	const navigate = useNavigate();

	const onSubmit = loginRequest => {
		setTimeout(() => {
			login(loginRequest)
				.then(loginRequest => {
					setIsLoginSuccess(true);
					setTimeout(() => {
						authCtx.login(loginRequest);
					}, 2000);
				})
				.catch(error => {
					console.log(error);
				});
		}, 1000);
		navigate('/posts');
	};
	return (
		<div>
			<h3>Login</h3>
			<LoginRegister isSubmitted={isLoginSuccess} onSubmit={onSubmit} />
		</div>
	);
};

export default Login;

import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { register } from '../context/Api';
import RegisterForm from './forms/RegisterForm';

function Register() {
	const [isRegistrationSuccess, setIsRegistrationSuccess] = useState(false);
	const [registrationMessage, setRegistrationMessage] = useState('');
	const navigate = useNavigate();

	// const onSubmit = data =>;

	useEffect(() => {
		setTimeout(() => {
			if (isRegistrationSuccess) {
				setIsRegistrationSuccess(false);
				navigate('/login');
			}
		}, 2000);
	}, [isRegistrationSuccess]);

	const onSubmit = values => {
		setTimeout(() => {
			register(values)
				.then(data => {
					console.log(data);
				})
				.catch(err => {
					console.error(err);
				});
		});
	};
	return (
		<div>
			<h3>Register</h3>
			<RegisterForm onSubmit={onSubmit} />
		</div>
	);
}

export default Register;

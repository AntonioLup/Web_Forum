import React from 'react';
import { useForm } from 'react-hook-form';
import './forms.css';

const LoginRegister = ({ onSubmit, isSubmitted }) => {
	const {
		register,
		handleSubmit,

		watch,
		formState: { errors },
	} = useForm();
	return (
		<div>
			<picture>
				<img src='https://www.svgrepo.com/show/506239/login.svg' />
			</picture>
			<form className='form' onSubmit={handleSubmit(onSubmit)}>
				<div className='form-container'>
					<label htmlFor='name'>name:</label>
					<input
						className={`form-input ${
							errors.userName ? 'error-input' : 'success-input'
						}`}
						id='name'
						autoComplete='name'
						type='text'
						placeholder='write your name...'
						{...register('userName', { required: true })}
					/>
				</div>
				<div className='form-container'>
					<label htmlFor='password'>password:</label>
					<input
						className={`form-input ${
							errors.password ? 'error-input' : 'success-input'
						}`}
						id='password'
						type='password'
						autoComplete='current-password'
						placeholder='******...'
						{...register('password', { required: true })}
					/>
				</div>
				<input onClick={() => null} className='form-button' type='submit' />
			</form>
		</div>
	);
};

export default LoginRegister;

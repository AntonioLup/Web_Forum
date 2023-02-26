import React from 'react';
import { useForm } from 'react-hook-form';
import './forms.css';

const RegisterForm = ({ onSubmit }) => {
	const {
		register,
		handleSubmit,

		watch,
		formState: { errors },
	} = useForm();

	return (
		<div>
			<picture className='form-img'>
				<img src='https://www.svgrepo.com/show/408384/user-person-profile-check.svg' />
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
					<label htmlFor='email'>email:</label>
					<input
						className={`form-input ${
							errors.email ? 'error-input' : 'success-input'
						}`}
						id='email'
						autoComplete='email'
						type='text'
						placeholder='email@gmail.com...'
						{...register('email', { required: true })}
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
				<div className='form-container'>
					<label htmlFor='cpassword'>confirm:</label>
					<input
						className={`form-input ${
							errors.confirm_password ? 'error-input' : 'success-input'
						}`}
						id='cpassword'
						type='password'
						placeholder='confirm password'
						{...register('confirm_password', {
							required: true,
							validate: val => {
								if (watch('password') !== val) {
									return 'Your passwords do no match';
								}
							},
						})}
					/>
				</div>
				<input onClick={() => null} className='form-button' type='submit' />
			</form>
		</div>
	);
};

export default RegisterForm;

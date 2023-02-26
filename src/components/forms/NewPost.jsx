import React, { useContext, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../../context/AuthContext';
import { register, submitNewPost } from '../../context/Api';
import { useForm } from 'react-hook-form';
import './forms.css';

function NewPost({ onSubmit, isSubmitted }) {
	const {
		register,
		handleSubmit,
		watch,
		formState: { errors },
	} = useForm();
	const textAreaRef = useRef(null);
	const authCtx = useContext(AuthContext);
	return (
		<div>
			<picture>
				<img src='https://www.svgrepo.com/show/506784/add-square.svg' />
			</picture>
			<form className='form' onSubmit={handleSubmit(onSubmit)}>
				<div className='form-container'>
					<label htmlFor='title'>title:</label>
					<input
						className={`form-input ${
							errors.title ? 'error-input' : 'success-input'
						}`}
						id='title'
						autoComplete='title'
						type='text'
						placeholder='write your title...'
						{...register('title', { required: true })}
					/>
				</div>
				<div className='form-container'>
					<label htmlFor='content'>content:</label>
					<textarea
						className={`form-text-area ${
							errors.content ? 'error-input' : 'success-input'
						}`}
						id='content'
						type='text'
						autoComplete='current-password'
						placeholder='write your article...'
						{...register('content', { required: true })}
					/>
				</div>
				<input onClick={() => null} className='form-button' type='submit' />
			</form>
		</div>
	);
}

export default NewPost;

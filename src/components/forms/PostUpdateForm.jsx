import React, { useContext, useState } from 'react';
import { useForm } from 'react-hook-form';
import AuthContext from '../../context/AuthContext';
import './forms.css';

function PostUpdateForm({
	post,
	onSubmit,
	onPostDelete,
	isDeletingPost,
	isPostDeleted,
}) {
	const [edit, setEdit] = useState(false);
	const {
		register,
		handleSubmit,
		watch,
		formState: { errors },
	} = useForm();
	const handleEdit = e => {
		e.preventDefault();
		if (edit === true) {
			setEdit(false);
		} else {
			setEdit(true);
		}
	};

	const authCtx = useContext(AuthContext);
	return (
		<div>
			<form className='form' onSubmit={handleSubmit(onSubmit)}>
				<div className='form-container'>
					<div className='title'>
						<h3>{post.title}</h3>
					</div>
				</div>
				<div className='form-container'>
					{!edit ? (
						<p className='content'>{post.content}</p>
					) : (
						<>
							<label htmlFor='content'>content:</label>
							<textarea
								className={`form-text-area ${
									errors.content ? 'error-input' : 'success-input'
								}`}
								id='content'
								onChange={e => e.target.value}
								type='text'
								autoComplete='current-password'
								placeholder='write your article...'
								{...register('content', { required: true })}
							/>
						</>
					)}
				</div>
				{edit && (
					<input
						onClick={() => null}
						value={'Update'}
						className='form-button'
						type='submit'
					/>
				)}
				<input
					onClick={e => handleEdit(e)}
					value={edit ? 'Article' : 'Edit'}
					className='form-button'
					type='button'
				/>
				<input
					onClick={onPostDelete}
					value={'Delete'}
					className='form-button'
					type='submit'
				/>
			</form>
		</div>
	);
}

export default PostUpdateForm;

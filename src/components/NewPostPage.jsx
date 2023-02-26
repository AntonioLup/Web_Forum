import React, { useContext, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { submitNewPost } from '../context/Api';
import AuthContext from '../context/AuthContext';
import NewPost from './forms/NewPost';

function NewPostPage() {
	const [isSubmitted, setIsSubmitted] = useState(false);
	const [newPostId, setNewPostId] = useState(null);
	const authContext = useContext(AuthContext);
	const navigate = useNavigate();

	const onSubmit = newPost => {
		console.log(newPost);
		submitNewPost(newPost, authContext.token)
			.then(post => {
				setTimeout(() => {
					setNewPostId(post.id);
					setIsSubmitted(true);
				}, 2000);
			})
			.catch(err => {
				console.log(err);
			});
	};
	useEffect(() => {
		setTimeout(() => {
			if (isSubmitted) {
				setIsSubmitted(false);
				navigate(`/post/${newPostId}`);
			}
		});
	});
	return (
		<div>
			<h3>New Post</h3>
			<NewPost isSubmitted={isSubmitted} onSubmit={onSubmit} />
		</div>
	);
}

export default NewPostPage;

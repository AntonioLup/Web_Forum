import React, { useContext, useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { deletePost, getPostById, updatePost } from '../context/Api';
import AuthContext from '../context/AuthContext';
import PostUpdateForm from './forms/PostUpdateForm';

function PostDetails(props) {
	const [post, setPost] = useState([]);
	const [isLoading, setIsLoading] = useState(true);
	const [isPostUpdated, setIsPostUpdated] = useState(false);
	const [isPostDeleted, setIsPostDeleted] = useState(false);
	const [isDeletingPost, setIsDeletingPost] = useState(false);
	const [error, setError] = useState(null);
	const { id } = useParams();
	const authCtx = useContext(AuthContext);
	const navigate = useNavigate();
	console.log(post);

	const onPostUpdate = updatedContent => {
		setTimeout(() => {
			updatePost(
				{ id: post.id, content: updatedContent.content },
				authCtx.token
			)
				.then(() => {
					// console.log("Post updated");
					setIsPostUpdated(true);
				})
				.catch(error => {
					console.log(error);
				});
		}, 1000);
	};

	const fetchPostById = () => {
		setIsLoading(true);
		getPostById(id)
			.then(post => {
				setPost(post);
			})
			.catch(error => {
				console.log(error);
				setError(error.message);
			})
			.finally(() => {
				setIsLoading(false);
			});
	};
	const onPostDelete = () => {
		setIsDeletingPost(true);
		setTimeout(() => {
			deletePost(post.id, authCtx.token)
				.then(() => {
					setIsPostDeleted(true);
				})
				.catch(error => {
					console.log(error);
				})
				.finally(() => {
					setIsDeletingPost(false);
				});
		}, 1000);
	};

	useEffect(() => {
		setTimeout(() => {
			if (isPostUpdated) {
				setIsPostUpdated(false);
				fetchPostById();
			}
			if (isPostDeleted) {
				setIsPostDeleted(false);
				navigate('/home');
			}
		}, 1000);
	}, [isPostUpdated, isPostDeleted]);

	useEffect(() => {
		setTimeout(() => {
			fetchPostById();
		}, 1000);
	}, []);

	let content = 'Loading..';

	if (!isLoading && !error) {
		content = (
			<PostUpdateForm
				post={post}
				onSubmit={onPostUpdate}
				onPostDelete={onPostDelete}
				isDeletingPost={isDeletingPost}
				isPostDeleted={isPostDeleted}
			/>
		);
	}

	return (
		<div>
			<h3>post: {id}</h3>
			<div>{content}</div>
		</div>
	);
}

export default PostDetails;

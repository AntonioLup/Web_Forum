import React, { useEffect, useState } from 'react';
import { getAllPosts } from '../context/Api';
import PostThumbnailsList from './PostThumbnailsList';
import './post.css';
const PostsList = props => {
	const [posts, setPosts] = useState([]);
	const [isLoading, setIsLoading] = useState(true);

	useEffect(() => {
		setTimeout(() => {
			getAllPosts().then(data => {
				setPosts(data);
				setIsLoading(false);
			});
		}, 1000);
	}, []);
	return (
		<div>
			<h3>Posts</h3>
			<div className='post-wrapper'>
				{isLoading && <p>Loading...</p>}
				<PostThumbnailsList posts={posts} />
			</div>
		</div>
	);
};

export default PostsList;

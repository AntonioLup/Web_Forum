import React from 'react';
import Posts from './Posts';

function PostThumbnailsList({ posts }) {
	return posts.map(post => {
		return <Posts key={post.id} post={post} />;
	});
}

export default PostThumbnailsList;

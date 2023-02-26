import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import './post.css';

function Posts({ post }) {
	return (
		<>
			<div className='post'>
				<h3>{post.title}</h3>
				<div>
					<p>{post.content.slice(0, 50) + ' . . .'}</p>
				</div>
				<div>
					<span>{post.createdOn}</span>
				</div>
				<Link className='link' as={NavLink} to={'/post/' + post.id}>
					<p>Read More</p>
				</Link>
			</div>
		</>
	);
}

export default Posts;

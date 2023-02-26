import React, { useContext } from 'react';
import { Outlet } from 'react-router-dom';
import Footer from '../components/Footer';
import '../App.css';
import Header from '../components/Header';
import Hero from '../components/Hero';
import AuthContext from '../context/AuthContext';

function Content() {
	const context = useContext(AuthContext);

	const { userName } = context;
	return (
		<div className='main-content'>
			<h1>Hey{', ' + userName || 'User'}</h1>
			<div className='outler'>
				<Header />
				<Hero />
			</div>

			<Footer />
		</div>
	);
}

export default Content;

import { useContext } from 'react';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import Hero from './components/Hero';
import Login from './components/Login';
import NewPost from './components/NewPostPage';
import PostDetails from './components/PostDetails';
import PostsList from './components/PostsList';
import Register from './components/Register';
import AuthContext from './context/AuthContext';
import Content from './page/Content';

function App() {
	const AuthCtext = useContext(AuthContext);
	const { isLoggedIn } = AuthCtext;
	return (
		<BrowserRouter>
			<Routes>
				<Route path='/' element={<Content />}>
					<Route element={<Hero />}>
						<Route path='/posts' element={<PostsList />} />
						{!isLoggedIn && <Route path='/register' element={<Register />} />}
						{!isLoggedIn && <Route path='/login' element={<Login />} />}
						<Route path='/new-post' element={<NewPost />} />
						<Route path='/post/:id' element={<PostDetails />} />
					</Route>
					<Route path='*' element={<Navigate to='/' />}></Route>
				</Route>
			</Routes>
		</BrowserRouter>
	);
}

export default App;

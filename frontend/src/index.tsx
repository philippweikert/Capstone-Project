import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import AuthProvider from "./Authentification/AuthProvider";
import MainPage from "./UserSurface/MainPage";
import RegisterPage from "./UserSurface/RegisterPage";
import LoginPage from "./UserSurface/LoginPage";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <AuthProvider>
               <Routes>
                   <Route path={'/'} element={<App/>}>
                       <Route path={'/home'} element={<MainPage/>}/>
                       <Route path={'/login'} element={<LoginPage/>}/>
                       <Route path={'/register'} element={<RegisterPage/>}/>
                   </Route>
               </Routes>
            </AuthProvider>
        </BrowserRouter>
    </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

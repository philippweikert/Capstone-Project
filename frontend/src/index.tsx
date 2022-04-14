import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import AuthProvider from "./Authentification/AuthProvider";
import AdminForm from "./UserSurface/AdminForm";
import Login from "./Landing/Login";
import Registerpage from "./Landing/Registerpage";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <AuthProvider>
               <Routes>
                   <Route path={'/'} element={<App/>}>
                       <Route path={'/home'} element={<AdminForm/>}/>
                       <Route path={'/login'} element={<Login/>}/>
                       <Route path={'/register'} element={<Registerpage/>}/>
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

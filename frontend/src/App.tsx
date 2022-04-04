import {BrowserRouter, Routes, Route} from "react-router-dom";
import AuthProvider from "./Authentification/AuthProvider";
import PlantForm from "./PlantForm";
import Registerpage from "./Landing/Registerpage";
import Login from "./Landing/Login";


function App() {


    return (
        <div>
            <BrowserRouter>
                <AuthProvider>
                    <Login/>
                    <Registerpage/>
                </AuthProvider>
            </BrowserRouter>
        </div>
    );
}

export default App;

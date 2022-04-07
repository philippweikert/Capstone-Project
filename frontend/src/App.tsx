import {BrowserRouter} from "react-router-dom";
import AuthProvider from "./Authentification/AuthProvider";
import Registerpage from "./Landing/Registerpage";
import Login from "./Landing/Login";
import AdminForm from "./UserSurface/AdminForm";


function App() {


    return (
        <div>
            <BrowserRouter>
                <AuthProvider>
                    <Login/>
                    <Registerpage/>
                    <AdminForm/>
                </AuthProvider>
            </BrowserRouter>
        </div>
    );
}

export default App;

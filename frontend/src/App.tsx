import {BrowserRouter} from "react-router-dom";
import AuthProvider from "./Authentification/AuthProvider";
import Registerpage from "./Landing/Registerpage";
import Login from "./Landing/Login";
import PlantForm from "./PlantForm";


function App() {


    return (
        <div>
            <BrowserRouter>
                <AuthProvider>
                    <Login/>
                    <Registerpage/>
                    <PlantForm/>
                </AuthProvider>
            </BrowserRouter>
        </div>
    );
}

export default App;

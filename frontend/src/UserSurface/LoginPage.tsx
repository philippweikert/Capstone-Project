
import Login from "../Components/Login";
import Registerpage from "../Components/Registerpage";
import LoginHeader from "../Components/LoginHeader";

export default function LoginPage(){

    return(
        <div>
            <LoginHeader/>
            <Login/>
            <Registerpage/>
        </div>
    )
}
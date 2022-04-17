import {useNavigate} from "react-router-dom";
import {useAuth} from "../Authentification/AuthProvider";

export default function Header (){

    const navigation = useNavigate()

    const auth = useAuth();

    const pathRoute = (where : string) => {
        navigation("/" + where)
    }

    return(
        <div>
            <div><button onClick={() => pathRoute('login')}>Zum Login</button></div>
            <div><button onClick={() => pathRoute('register')}>Registriere dich hier</button></div>
            <div><button onClick={() => auth.logout()}>Logout</button></div>
        </div>
    )
}
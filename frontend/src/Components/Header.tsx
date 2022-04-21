
import {useAuth} from "../Authentification/AuthProvider";

export default function Header (){

    const auth = useAuth();

    return(
        <div>
            <h3>Hallo {auth.username}, herzlich willkommen!</h3>
            <div><button onClick={() => auth.logout()}>Logout</button></div>
        </div>
    )
}
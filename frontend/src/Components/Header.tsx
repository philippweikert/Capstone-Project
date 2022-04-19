
import {useAuth} from "../Authentification/AuthProvider";

export default function Header (){

    const auth = useAuth();

    return(
        <div>
            <div><button onClick={() => auth.logout()}>Logout</button></div>
        </div>
    )
}
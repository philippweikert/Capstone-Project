
import {useAuth} from "../Authentification/AuthProvider";
import LogoutButton from "../CssComponents/LogoutButton";

export default function Header (){

    const auth = useAuth();

    return(
        <div>
        <div className="flex items-center flex-shrink-0 text-indigo-100 bg-slate-400">
            <p className="font-semibold text-xl tracking-tight">Hallo {auth.username}, herzlich willkommen!</p>
            <LogoutButton label="Logout" onClick={() => auth.logout()}/>
        </div>
        </div>
    )
}
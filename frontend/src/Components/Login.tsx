import {FormEvent, useState} from "react";
import {useAuth} from "../Authentification/AuthProvider";
import {useNavigate} from "react-router-dom";
import Button from "../CssComponents/Button";
import LrInput from "../CssComponents/LrInput";

export default function Login(){
    const [username, setUsername] = useState("");
    const [usedPassword, setUsedPassword] = useState("");
    const [error, setError] = useState("");

    const auth = useAuth();
    const navigation = useNavigate()

    const loginAction = (event: FormEvent) => {
        event.preventDefault()
        setError("")
        auth.login(username, usedPassword)
            .then(() => navigation("home"))
            .catch(error => setError(error.message))
    }

    return(
        <div>
            <h3>Hallo lieber Besucher, hier kannst du dich einloggen und/oder registrieren!</h3>
            <h4>Die App soll dir dabei helfen, deinen Pflanzen ein schönes Leben zu bieten.</h4>
            <form onSubmit={loginAction}>
                <LrInput placeholder={'Benutzername'} value={username} onChange={setUsername} type='text' additionalCss="mr-4 ml-3"/>
                <LrInput placeholder={'Passwort'} value={usedPassword} onChange={setUsedPassword} type='password' additionalCss="mr-4"/>
                <div>
                <Button label='Los gehts!' onClick={() => loginAction}/>
                </div>
            </form>
            {error && <h2>{error}</h2>}
        </div>
    )
}

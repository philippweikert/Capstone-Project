import {FormEvent, useState} from "react";
import {registerUser} from "../service/FrontendService";
import {useNavigate} from "react-router-dom";
import Button from "../CssComponents/Button";
import LrInput from "../CssComponents/LrInput";

export default function Registerpage(){
    const [registerUsername, setRegisterUsername] = useState('');
    const [registerPassword, setRegisterPassword] = useState('');
    const [registerRepeatPassword, setRegisterRepeatPassword] = useState('');
    const [error, setError] = useState('');

    const navigate = useNavigate()

    const handleRegister = (event:FormEvent) => {
        event.preventDefault()
        setError('')
        if(!(registerPassword===registerRepeatPassword)){
            setError('Passwörter stimmen nicht überein!')
        }else{
            registerUser({username:registerUsername, password:registerPassword, repeatPassword:registerRepeatPassword})
                .then(()=> {
                    setRegisterUsername("")
                    setRegisterPassword("")
                    setRegisterRepeatPassword("")
                })
                .then(() => navigate("login"))
                .catch(er => setError(er.message))
        }
    }

    return(
    <div>
        <h3>Registriere dich hier!</h3>
        <form onSubmit={handleRegister}>
            <LrInput placeholder={'Benutzername'} value={registerUsername} onChange={setRegisterUsername} type='text' additionalCss="mr-4 ml-3"/>
            <LrInput placeholder={'Passwort'} value={registerPassword} onChange={setRegisterPassword} type='password' additionalCss="mr-4"/>
            <LrInput placeholder={'Wiederholung Passwort'} value={registerRepeatPassword} onChange={setRegisterRepeatPassword} type='password' additionalCss="mr-4"/>
            <div>
                <Button label='Registriere dich hier' onClick={() => handleRegister}/>
            </div>
        </form>
        {error && <h2>{error}</h2>}
    </div>)
}
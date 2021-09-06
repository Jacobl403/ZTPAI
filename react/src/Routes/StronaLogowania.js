import { useState } from 'react'
import { useHistory } from 'react-router-dom'
import { useEffect } from 'react/cjs/react.development';
import '../css/logowanie.css'

const StronaLogowania = (props) => {
    const delay = ms => new Promise(res => setTimeout(res, ms));
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    let history = useHistory()
    const zaloguj = async () => {


        const response = await fetch ('http://localhost:8080/zaloguj', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type' : 'application/json'
            },
            body: JSON.stringify({
                email: username,
                password: password,
                token: null
            })
        })
        
        const r = await response.json();
     

        await delay(500)
        localStorage.setItem('token', r.token)
        window.location.reload()
    }
    
    const przekieruj = () => {
        history.push('/rejestracja')
    }

    useEffect(() => {
        if(localStorage.getItem('token')){
            history.push('/aktualnosci')
        }
    }, [localStorage.getItem('token')])

    return (
        <div className="mainStronaLogowania">
            <div className="subMainStronaLogowania">
                <h2>Zaloguj się!</h2>
                <input type="email" name="email" placeholder="Email" value={username || ""} onChange={e => setUsername(e.target.value)}></input>
                <input type="password" name="password" placeholder="Password" value={password || ""} onChange={e => setPassword(e.target.value)}></input>
                <button type="submit" name="submit" onClick={zaloguj}>Zaloguj</button>
                <h2>Nie masz konta?</h2>
                <button onClick={() => przekieruj()}>Chcę utworzyć konto.</button>
            </div>
        </div>
    );
}
 
export default StronaLogowania;

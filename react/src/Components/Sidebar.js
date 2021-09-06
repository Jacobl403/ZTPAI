import '../css/sidebar.css'
import { Link } from 'react-router-dom'
import { useEffect, useState } from 'react'
const Sidebar = (props) => {
    const [logged, setLogged] = useState('Zaloguj się')
    const [renderThis, setRenderThis] = useState('')

    function handleClick(){
        localStorage.clear()
        window.location.reload()
    }

    useEffect(() => {
        
        if(props.stan === 1){
            setLogged('Wyloguj się')
            setRenderThis('')
        }
        if(props.stan === 0) setRenderThis(<Link to ="/rejestracja">Rejestracja</Link>)
    }, [props.stan])
    return (
        <div className="mainSidebar">
            <div className="subSidebar">
                <Link to ="/aktualnosci">Aktualności</Link>
                <Link to ="/edycjaprofilu">Edycja Profilu</Link>
                <Link to ="/planzajec">Plan Zajęć</Link>
                <Link to ="/notatki">Notatki</Link>
            </div>
            <div className="userSidebar">
                <Link to ="/" onClick={handleClick}>{logged}</Link>
                {renderThis}
            </div>
        </div>
    );
}
 
export default Sidebar;

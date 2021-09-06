import React from 'react'
import WyswietlAkt from '../Components/WyswietlAkt'
import '../css/aktualnosci.css'

const Aktualnosci = () => {
    return (
        <div className="mainAktualnosci">
            <h2>Aktualności</h2>
            <WyswietlAkt/>
        </div>
    );
}
 
export default Aktualnosci;
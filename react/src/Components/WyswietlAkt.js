import '../css/aktualnosci.css'
import { useState, useEffect } from 'react'
import axios from 'axios'

const WyswietlAkt = () => {

    const [userActivites, setUserActivities] = useState(['Brak aktywności'])
    let token = null
    if(localStorage.getItem('token')){
        token = localStorage.getItem('token')
    }

    const getActivities = async () => {
        const response = await axios.get("http://localhost:8080/aktualnosci", {
            headers: { 'Authorization' : 'Bearer ' + token }
        })
        
        setUserActivities(response.data.activities)
        
    }

    useEffect(() => {
        getActivities()
    }, [])

    return (
        <div>
            {/* {userActivites.map((txt, i) => 
                <div className="aktualnosc" key={i}>{txt}</div>
            )} */}
            {
                (userActivites.length === 0 ) ? (
                    <div className="mainWyswietlAkt2">
                        <div className="brakAktualnosci">Brak nowych aktualnosci :(</div>
                    </div>
                ) : (
                    <div className="mainWyswietlAkt">
                        {
                            // userActivites.map((txt, i) => 
                            //     <div className="aktualnosc" key={txt}>{txt}</div>
                            // )
                                userActivites.map((txt, i) => 
                                <div className="aktualnosc" key={i}>{txt.content}</div>
                            )
                        }
                    </div>
                )
            }
        </div>
    );
}
 
export default WyswietlAkt;

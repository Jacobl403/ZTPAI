import { useState } from 'react'
import "../css/materialy.css"

const Mat = props => props.data.map(e => <div className="uploadedFiles">{e}</div>)

const Materialy = () => {
    let subjects = ["PUS", "ZTPAI", "ENGLISH", "DSAA", "OBD", "ANALIZA", "ALGEBRA", "FIZYKA", "NOKIA"]
    const [selectedFile, setSelectedFile] = useState(null)

    const onFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    }

    const onFileUpload = (subj) => {
        const formData = new FormData();
        formData.append(
            "myFile",
            selectedFile,
            selectedFile.name,
        )
        if(localStorage.getItem(`uploadedFiles${subj}`)){
            let arr = JSON.parse(localStorage.getItem(`uploadedFiles${subj}`))
            arr.push(selectedFile.name)
            let jsn = JSON.stringify(arr)
            localStorage.setItem(`uploadedFiles${subj}`, jsn)
        }else{
            localStorage.setItem(`uploadedFiles${subj}`, selectedFile.name)
        }
        //w bazie danych musi byc identyfikator mówiący do jakiego przedmiotu odnośni się plik 
        //żeby można było je wyświetlać w odpowiednimi divie
        //axios.post("localhost:4000/fileupload", formData)
    }

    // const uploadedFiles = (x) => {
    //     let arr = JSON.parse(localStorage.getItem(`uploadedFiles${x}`))
    //     console.log(localStorage.getItem(`uploadedFiles${x}`))
    //     // if(arr){
    //     //     return(
    //     //         <div>{arr}</div>
    //     //     )
    //     // }else {
    //     //     return(
    //     //         <div>Brak plików w bazie.</div>
    //     //     )
    //     // }
    // }

    const fileData = () => {
        console.log('fileData run')
        if(selectedFile) {
            return(
                <div>
                    <h2>Szczegóły pliku:</h2>
                    <p>Nazwa pliku: {selectedFile.name}</p>
                    <p>Typ pliku: {selectedFile.type}</p>
                </div>
            );
        }else {
            return (
                <div>
                    <br></br>
                    <h4>Wybierz plik przed uploadem.</h4>
                </div>
            )
        }
    }
    return (
        <div className="mainMaterialy">
            <h2>Materiały</h2>
            {fileData()}
            <div className="subMainMaterialy">
                {
                    subjects.map((e, i) => (
                        <div className="mainUpload" id={e} key={i}>
                            <div className="subjectName">{e}</div>
                            <div className="uploadPliku">
                                <input type="file" className="inp" onChange={onFileChange} />
                                <button onClick={() => onFileUpload(e)}>Wyślij</button>
                            </div>
                        </div>
                    ))
                }
            </div>
        </div>
    );
}
 
export default Materialy;
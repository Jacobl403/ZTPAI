import axios from "axios";

const GetUserSubjects = async () => {
    const response = await axios.get("http://localhost:8080/edycjaprofilu")

    return response.data.Subjects
    //return response.Subjects
}

export default GetUserSubjects;